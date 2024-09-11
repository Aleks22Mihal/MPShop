package mpshop.app.presentation.screens.homeScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import mpshop.app.common.Result
import mpshop.app.common.onError
import mpshop.app.common.onSuccess
import mpshop.app.domain.useCase.AddInFavoriteUseCase
import mpshop.app.domain.useCase.DeleteInFavoriteUseCase
import mpshop.app.domain.useCase.GetAllCategoriesUseCase
import mpshop.app.domain.useCase.GetLimitProductsUseCase
import mpshop.app.domain.useCase.SearchProductsUseCase
import mpshop.app.presentation.models.Product
import mpshop.app.presentation.navigation.homeNavigation.HomeConfiguration
import mpshop.app.presentation.screens.homeScreen.data.HomeScreenEvent
import mpshop.app.presentation.screens.homeScreen.data.HomeScreenState
import mpshop.app.presentation.utils.CoroutineScopeInstance
import mpshop.app.presentation.utils.LoadState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class HomeScreenComponentImpl(
    componentContext: ComponentContext,
    mainContext: CoroutineContext,
    private val onNavigation: (HomeConfiguration) -> Unit,
) : HomeScreenComponent, ComponentContext by componentContext, KoinComponent {

    private val _state = MutableValue(HomeScreenState())
    override val state: Value<HomeScreenState> = _state

    private val scopeInstance = instanceKeeper.getOrCreate { CoroutineScopeInstance(mainContext) }
    private val scope = scopeInstance.scope

    private val getLimitProductsUseCase by inject<GetLimitProductsUseCase>()
    private val getAllCategoriesUseCase by inject<GetAllCategoriesUseCase>()
    private val addInFavoriteUseCase by inject<AddInFavoriteUseCase>()
    private val deleteInFavoriteUseCase by inject<DeleteInFavoriteUseCase>()
    private val searchProductsUseCase by inject<SearchProductsUseCase>()


    init {
        init()
    }


    override fun onEvent(event: HomeScreenEvent) {
        when (event) {

            HomeScreenEvent.Refresh -> init()

            is HomeScreenEvent.ChangeSearchText -> _state.value = state.value.copy(
                searchBarText = event.text
            )

            HomeScreenEvent.OnClickCategory -> onNavigation(
                HomeConfiguration.Category
            )

            is HomeScreenEvent.OnClickListProduct -> onNavigation(
                HomeConfiguration.ListProduct(
                    nameCategory = event.nameCategory,
                    categoryId = event.categoryId
                )
            )

            is HomeScreenEvent.OnClickProduct -> onNavigation(
                HomeConfiguration.Product(
                    productId = event.productId
                )
            )

            is HomeScreenEvent.AddInFavorite -> addInFavorite(event.product)

            is HomeScreenEvent.DeleteInFavorite -> deleteInFavorite(event.productId)

            HomeScreenEvent.ActiveChangeSearchBar -> isActiveSearchBar()

            is HomeScreenEvent.ChangeSearchBarText -> changeSearchBarText(event.queryText)

            is HomeScreenEvent.ShowBottomSheet -> _state.value = state.value.copy(
                showBottomSheet = event.isShowType
            )
        }
    }

    private fun init() {
        scope.launch {
            try {
                val categoriesDeferred = async {
                    getAllCategoriesUseCase()
                }
                val topProductsDeferred = async {
                    getLimitProductsUseCase(limit = 5)
                }
                val saleProductsDeferred = async {
                    getLimitProductsUseCase(limit = 5)
                }
                val newCollectionProductDeferred = async {
                    getLimitProductsUseCase(limit = 5)
                }

                val categories = categoriesDeferred.await()
                val topProducts = topProductsDeferred.await()
                val saleProducts = saleProductsDeferred.await()
                val newCollectionProduct = newCollectionProductDeferred.await()

                if (categories is Result.Success &&
                    topProducts is Result.Success &&
                    saleProducts is Result.Success &&
                    newCollectionProduct is Result.Success
                ) {
                    _state.value = state.value.copy(
                        categories = categories.data,
                        topProducts = topProducts.data,
                        saleProducts = saleProducts.data,
                        newCollectionProducts = newCollectionProduct.data,
                        loadState = LoadState.Successful
                    )
                } else if (
                    categories is Result.Error &&
                    topProducts is Result.Error &&
                    saleProducts is Result.Error &&
                    newCollectionProduct is Result.Error
                ) {
                    val errorMessage = ""
                    _state.value = state.value.copy(
                        loadState = LoadState.Error(errorMessage)
                    )
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun addInFavorite(product: Product) {
        _state.value = state.value.copy(
            loadStateAddInFavorite = LoadState.Loading
        )
        scope.launch {
            addInFavoriteUseCase(product).onSuccess {
                _state.value = state.value.copy(
                    loadStateAddInFavorite = LoadState.Successful
                )
            }.onError { error ->
                _state.value = state.value.copy(
                    loadStateAddInFavorite = LoadState.Error(error.name)
                )
            }
        }
    }

    private fun deleteInFavorite(productId: Int) {
        _state.value = state.value.copy(
            loadStateAddInFavorite = LoadState.Loading
        )
        scope.launch {
            deleteInFavoriteUseCase(productId).onSuccess {
                _state.value = state.value.copy(
                    loadStateAddInFavorite = LoadState.Successful
                )
            }.onError { error ->
                _state.value = state.value.copy(
                    loadStateAddInFavorite = LoadState.Error(error.name)
                )
            }
        }
    }

    private fun isActiveSearchBar() {
        _state.value = state.value.copy(
            isActiveSearchBar = !state.value.isActiveSearchBar
        )
        if (!state.value.isActiveSearchBar) {
            _state.value = state.value.copy(
                searchListProducts = emptyList(),
                searchBarText = ""
            )
        }
    }

    private fun changeSearchBarText(queryText: String) {
        _state.value = state.value.copy(
            searchBarText = queryText
        )
        if (state.value.searchBarText.isNotBlank()) {
            scope.launch {
                searchProductsUseCase(
                    title = state.value.searchBarText,
                    categoryId = null,
                    priceMin = null,
                    priceMax = null
                ).onSuccess {
                    _state.value = state.value.copy(
                        searchListProducts = it
                    )
                }
            }
        } else _state.value = state.value.copy(
            searchListProducts = emptyList()
        )
    }
}
