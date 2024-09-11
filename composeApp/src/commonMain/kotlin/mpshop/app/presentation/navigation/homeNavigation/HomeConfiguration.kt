package mpshop.app.presentation.navigation.homeNavigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface HomeConfiguration {

    @Serializable
    data object Home : HomeConfiguration

    @Serializable
    data object Category : HomeConfiguration

    @Serializable
    data class ListProduct(
        val categoryId: Int? = null,
        val nameCategory: String
    ) : HomeConfiguration

    @Serializable
    data class Product(val productId: String) : HomeConfiguration
}