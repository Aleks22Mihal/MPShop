package mpshop.app.presentation.navigation.shoppingCartNavigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface ShoppingCartConfiguration {

    @Serializable
    data object ShoppingCart : ShoppingCartConfiguration

    @Serializable
    data class Product(val productId: String) : ShoppingCartConfiguration

}