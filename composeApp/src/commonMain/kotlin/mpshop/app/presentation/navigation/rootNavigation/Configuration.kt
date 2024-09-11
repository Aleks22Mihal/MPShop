package mpshop.app.presentation.navigation.rootNavigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Configuration {
    @Serializable
    data object Home : Configuration

    @Serializable
    data object ShoppingCart : Configuration

    @Serializable
    data object Favorite : Configuration

    @Serializable
    data object Profile : Configuration
}