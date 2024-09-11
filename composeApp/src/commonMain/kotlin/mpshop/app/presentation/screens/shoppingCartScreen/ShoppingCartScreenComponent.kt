package mpshop.app.presentation.screens.shoppingCartScreen

import com.arkivanov.decompose.value.Value
import mpshop.app.presentation.screens.shoppingCartScreen.data.ShoppingCartScreenEvent
import mpshop.app.presentation.screens.shoppingCartScreen.data.ShoppingCartScreenState

interface ShoppingCartScreenComponent {
    val state: Value<ShoppingCartScreenState>

    fun onEvent(event: ShoppingCartScreenEvent)
}