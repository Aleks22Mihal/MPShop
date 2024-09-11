package mpshop.app.presentation.screens.shoppingCartScreen.data

import mpshop.app.presentation.models.Product
import mpshop.app.presentation.utils.LoadState

data class ShoppingCartScreenState(
    val products: List<Product> = emptyList(),
    val loadState: LoadState = LoadState.Successful
)