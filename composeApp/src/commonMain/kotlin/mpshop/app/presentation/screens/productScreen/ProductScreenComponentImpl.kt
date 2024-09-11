package mpshop.app.presentation.screens.productScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.launch
import mpshop.app.common.onError
import mpshop.app.common.onSuccess
import mpshop.app.domain.useCase.GetProductUseCase
import mpshop.app.presentation.screens.productScreen.data.ProductScreenEvent
import mpshop.app.presentation.screens.productScreen.data.ProductScreenState
import mpshop.app.presentation.utils.CoroutineScopeInstance
import mpshop.app.presentation.utils.LoadState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class ProductScreenComponentImpl(
    componentContext: ComponentContext,
    mainContext: CoroutineContext,
    private val productId: String,
    private val onBack: () -> Unit
) : ProductScreenComponent, ComponentContext by componentContext, KoinComponent {

    private val _state = MutableValue(ProductScreenState())
    override val state: Value<ProductScreenState> = _state

    private val scopeInstance = instanceKeeper.getOrCreate { CoroutineScopeInstance(mainContext) }
    private val scope = scopeInstance.scope

    private val getProductUseCase: GetProductUseCase by inject()

    init {
        init()
    }

    override fun onEvent(event: ProductScreenEvent) {
        when (event) {

            ProductScreenEvent.OnClickBack -> onBack()

            ProductScreenEvent.Refresh -> init()

            ProductScreenEvent.CountProductMinus -> {
                if (state.value.countProduct > 1) {
                    val count = state.value.countProduct
                    _state.value = state.value.copy(
                        countProduct = count - 1
                    )
                }
            }

            ProductScreenEvent.CountProductPlus -> {
                if (state.value.countProduct >= 1) {
                    val count = state.value.countProduct
                    _state.value = state.value.copy(
                        countProduct = count + 1
                    )
                }
            }

            is ProductScreenEvent.SelectColor -> {
                _state.value = state.value.copy(
                    selectColorIndex = event.colorIndex
                )
            }

            is ProductScreenEvent.SelectSize -> {
                _state.value = state.value.copy(
                    selectSizeIndex = event.sizeIndex
                )
            }

            is ProductScreenEvent.ShowBottomSheet -> {
                _state.value = state.value.copy(
                    showTypeBottomSheet = event.isShowType
                )
            }

            ProductScreenEvent.AddShoppingCart -> {}
        }
    }

    private fun init() {
        _state.value = state.value.copy(
            loadState = LoadState.Loading
        )
        scope.launch {
            try {
                getProductUseCase(productId = productId)
                    .onSuccess { product ->
                        _state.value = state.value.copy(
                            product = product,
                            loadState = LoadState.Successful
                        )
                    }
                    .onError { error ->
                        _state.value = state.value.copy(
                            loadState = LoadState.Error(error.name)
                        )
                    }
            } catch (e: Exception) {
                _state.value = state.value.copy(
                    loadState = LoadState.Error(e.toString())
                )
                e.printStackTrace()
            }
        }
    }
}
