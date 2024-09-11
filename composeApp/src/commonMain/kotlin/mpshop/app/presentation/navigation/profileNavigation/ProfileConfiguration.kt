package mpshop.app.presentation.navigation.profileNavigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface ProfileConfiguration {

    @Serializable
    data object Profile : ProfileConfiguration

}