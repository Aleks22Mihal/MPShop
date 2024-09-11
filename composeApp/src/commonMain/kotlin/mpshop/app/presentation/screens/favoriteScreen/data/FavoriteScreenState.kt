package mpshop.app.presentation.screens.favoriteScreen.data

import mpshop.app.presentation.models.Product
import mpshop.app.presentation.utils.LoadState

data class FavoriteScreenState(
    val listProducts: List<Product> = emptyList(),
    val loadState: LoadState = LoadState.Successful,
)
