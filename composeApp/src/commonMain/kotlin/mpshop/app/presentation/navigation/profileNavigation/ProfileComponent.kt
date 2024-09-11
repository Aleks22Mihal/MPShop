package mpshop.app.presentation.navigation.profileNavigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import mpshop.app.presentation.screens.profileScreen.ProfileScreenComponent

interface ProfileComponent {
    val stack: Value<ChildStack<ProfileConfiguration, ProfileChild>>

    fun onBack()

    fun onNavigation(screen: ProfileConfiguration)

    sealed class ProfileChild {
        class Profile(val component: ProfileScreenComponent) : ProfileChild()
    }
}