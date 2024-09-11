package mpshop.app.presentation.screens.favoriteScreen.data

sealed class FavoriteScreenEvent {

    data object Refresh : FavoriteScreenEvent()

    data class OnClickProduct(
        val productId: String
    ) : FavoriteScreenEvent()
}