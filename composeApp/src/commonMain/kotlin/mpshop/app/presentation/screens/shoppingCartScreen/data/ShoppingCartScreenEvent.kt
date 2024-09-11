package mpshop.app.presentation.screens.shoppingCartScreen.data

sealed class ShoppingCartScreenEvent {

    data object Refresh: ShoppingCartScreenEvent()

    data class OnClickProduct(
        val productId: String
    ) : ShoppingCartScreenEvent()
}