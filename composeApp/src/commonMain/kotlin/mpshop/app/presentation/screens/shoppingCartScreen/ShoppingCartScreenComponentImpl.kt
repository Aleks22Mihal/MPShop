package mpshop.app.presentation.screens.shoppingCartScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import mpshop.app.presentation.navigation.shoppingCartNavigation.ShoppingCartConfiguration
import mpshop.app.presentation.screens.shoppingCartScreen.data.ShoppingCartScreenEvent
import mpshop.app.presentation.screens.shoppingCartScreen.data.ShoppingCartScreenState
import org.koin.core.component.KoinComponent
import kotlin.coroutines.CoroutineContext

class ShoppingCartScreenComponentImpl(
    componentContext: ComponentContext,
    mainContext: CoroutineContext,
    private val onNavigation: (ShoppingCartConfiguration) -> Unit,
) : ShoppingCartScreenComponent, ComponentContext by componentContext, KoinComponent {

    private val _state = MutableValue(ShoppingCartScreenState())
    override val state: Value<ShoppingCartScreenState> = _state

    init {
        init()
    }

    override fun onEvent(event: ShoppingCartScreenEvent) {
        when (event) {

            ShoppingCartScreenEvent.Refresh -> init()

            is ShoppingCartScreenEvent.OnClickProduct -> onNavigation(
                ShoppingCartConfiguration.Product(
                    productId = event.productId
                )
            )
        }

    }

    private fun init() {

    }
}