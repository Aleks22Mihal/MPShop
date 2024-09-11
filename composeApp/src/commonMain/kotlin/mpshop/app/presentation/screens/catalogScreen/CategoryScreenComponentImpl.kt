package mpshop.app.presentation.screens.catalogScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.launch
import mpshop.app.common.onError
import mpshop.app.common.onSuccess
import mpshop.app.domain.useCase.GetAllCategoriesUseCase
import mpshop.app.presentation.navigation.homeNavigation.HomeConfiguration
import mpshop.app.presentation.screens.catalogScreen.data.CategoryScreenEvent
import mpshop.app.presentation.screens.catalogScreen.data.CategoryScreenState
import mpshop.app.presentation.utils.CoroutineScopeInstance
import mpshop.app.presentation.utils.LoadState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class CategoryScreenComponentImpl(
    componentContext: ComponentContext,
    mainContext: CoroutineContext,
    private val onNavigation: (HomeConfiguration) -> Unit,
    private val onBack: () -> Unit,
) : CategoryScreenComponent, ComponentContext by componentContext, KoinComponent {

    private val _state = MutableValue(CategoryScreenState())
    override val state: Value<CategoryScreenState> = _state

    private val scopeInstance = instanceKeeper.getOrCreate { CoroutineScopeInstance(mainContext) }
    private val scope = scopeInstance.scope

    private val getAllCategoriesUseCase by inject<GetAllCategoriesUseCase>()

    init {
        init()
    }

    override fun onEvent(event: CategoryScreenEvent) {
        when (event) {

            CategoryScreenEvent.Refresh -> init()

            CategoryScreenEvent.OnClickBack -> onBack()

            is CategoryScreenEvent.OnClickCategory -> onNavigation(
                HomeConfiguration.ListProduct(
                    categoryId = event.categoryId,
                    nameCategory = event.nameCategory
                )
            )

        }
    }

    private fun init() {
        _state.value = state.value.copy(
            loadState = LoadState.Loading
        )
        scope.launch {
            getAllCategoriesUseCase()
                .onSuccess { data ->
                    _state.value = state.value.copy(
                        listCategories = data,
                        loadState = LoadState.Successful
                    )
                }
                .onError { error ->
                    _state.value = state.value.copy(
                        loadState = LoadState.Error(error.name)
                    )
                }

        }
    }
}