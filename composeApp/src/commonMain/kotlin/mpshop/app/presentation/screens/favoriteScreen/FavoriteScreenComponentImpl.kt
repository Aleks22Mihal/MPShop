package mpshop.app.presentation.screens.favoriteScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import mpshop.app.common.Result
import mpshop.app.common.onError
import mpshop.app.common.onSuccess
import mpshop.app.domain.useCase.GetAllProductsInFavoriteUseCase
import mpshop.app.presentation.models.Product
import mpshop.app.presentation.navigation.favoriteNavigation.FavoriteConfiguration
import mpshop.app.presentation.screens.favoriteScreen.data.FavoriteScreenEvent
import mpshop.app.presentation.screens.favoriteScreen.data.FavoriteScreenState
import mpshop.app.presentation.utils.CoroutineScopeInstance
import mpshop.app.presentation.utils.LoadState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class FavoriteScreenComponentImpl(
    componentContext: ComponentContext,
    mainContext: CoroutineContext,
    private val onNavigation: (FavoriteConfiguration) -> Unit,
) : FavoriteScreenComponent, ComponentContext by componentContext, KoinComponent {

    private val _state = MutableValue(FavoriteScreenState())
    override val state: Value<FavoriteScreenState> = _state

    private val scopeInstance = instanceKeeper.getOrCreate { CoroutineScopeInstance(mainContext) }
    private val scope = scopeInstance.scope

    private val getAllProductsInFavoriteUseCase by inject<GetAllProductsInFavoriteUseCase>()

    init {
        init()
    }

    override fun onEvent(event: FavoriteScreenEvent) {
        when (event) {
            is FavoriteScreenEvent.OnClickProduct -> onNavigation(
                FavoriteConfiguration.Product(
                    productId = event.productId
                )
            )

            FavoriteScreenEvent.Refresh -> init()
        }
    }

    private fun init() {
        _state.value = state.value.copy(
            loadState = LoadState.Loading
        )
        scope.launch {
            getAllProductsInFavoriteUseCase().subscribe { data ->
                println("print data: $data")
                _state.value = state.value.copy(
                    listProducts = data,
                    loadState = LoadState.Successful
                )
            }
        }
    }
}