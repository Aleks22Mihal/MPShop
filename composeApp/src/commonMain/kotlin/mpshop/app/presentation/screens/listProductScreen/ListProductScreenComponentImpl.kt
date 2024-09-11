package mpshop.app.presentation.screens.listProductScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.launch
import mpshop.app.common.onError
import mpshop.app.common.onSuccess
import mpshop.app.domain.useCase.GetAllProductsUseCase
import mpshop.app.domain.useCase.GetProductFromCategoryUseCase
import mpshop.app.presentation.navigation.homeNavigation.HomeConfiguration
import mpshop.app.presentation.screens.listProductScreen.data.ListProductScreenEvent
import mpshop.app.presentation.screens.listProductScreen.data.ListProductScreenState
import mpshop.app.presentation.utils.CoroutineScopeInstance
import mpshop.app.presentation.utils.LoadState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class ListProductScreenComponentImpl(
    componentContext: ComponentContext,
    mainContext: CoroutineContext,
    private val categoryId: Int? = null,
    private val nameCategory: String,
    private val onNavigation: (HomeConfiguration) -> Unit,
    private val onBack: () -> Unit
) : ListProductScreenComponent, ComponentContext by componentContext, KoinComponent {

    private val _state = MutableValue(ListProductScreenState(nameCategory = nameCategory))
    override val state: Value<ListProductScreenState> = _state

    private val scopeInstance = instanceKeeper.getOrCreate { CoroutineScopeInstance(mainContext) }
    private val scope = scopeInstance.scope

    private val getAllProductsUseCase: GetAllProductsUseCase by inject()
    private val getProductFromCategoryUseCase: GetProductFromCategoryUseCase by inject()

    init {
        init()
    }

    override fun onEvent(event: ListProductScreenEvent) {
        when (event) {

            ListProductScreenEvent.OnClickBack -> onBack()

            is ListProductScreenEvent.OnClickProduct -> onNavigation(
                HomeConfiguration.Product(
                    event.productId
                )
            )

            ListProductScreenEvent.Refresh -> init()
        }
    }

    private fun init() {
        _state.value = state.value.copy(
            loadState = LoadState.Loading
        )
        scope.launch {
            try {

                if (categoryId != null) {
                    val result = getProductFromCategoryUseCase(categoryId = categoryId)
                    result
                        .onSuccess { listProduct ->
                            _state.value = state.value.copy(
                                loadState = LoadState.Successful,
                                listProducts = listProduct
                            )
                        }
                        .onError { error ->
                            _state.value = state.value.copy(
                                loadState = LoadState.Error(error.name),
                            )
                        }
                } else {
                    val result = getAllProductsUseCase()

                    result
                        .onSuccess { listProduct ->
                            _state.value = state.value.copy(
                                loadState = LoadState.Successful,
                                listProducts = listProduct
                            )
                        }
                        .onError { error ->
                            _state.value = state.value.copy(
                                loadState = LoadState.Error(error.name),
                            )
                        }
                }

            } catch (e: Exception) {
                e.printStackTrace()
                _state.value = state.value.copy(
                    loadState = LoadState.Error(e.toString()),
                )
            }
        }
    }
}
