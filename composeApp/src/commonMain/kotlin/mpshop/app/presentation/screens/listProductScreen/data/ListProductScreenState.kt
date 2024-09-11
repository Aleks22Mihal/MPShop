package mpshop.app.presentation.screens.listProductScreen.data

import mpshop.app.presentation.models.Product
import mpshop.app.presentation.utils.LoadState

data class ListProductScreenState(
    val nameCategory: String = "",
    val loadState: LoadState = LoadState.Successful,
    val listProducts: List<Product> = emptyList()
)