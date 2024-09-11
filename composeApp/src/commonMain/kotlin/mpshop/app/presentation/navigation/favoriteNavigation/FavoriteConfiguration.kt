package mpshop.app.presentation.navigation.favoriteNavigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface FavoriteConfiguration {

    @Serializable
    data object Favorite : FavoriteConfiguration

    @Serializable
    data class Product(val productId: String) : FavoriteConfiguration

}