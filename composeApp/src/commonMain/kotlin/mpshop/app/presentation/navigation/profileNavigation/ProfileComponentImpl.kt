package mpshop.app.presentation.navigation.profileNavigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.Dispatchers
import mpshop.app.presentation.screens.profileScreen.ProfileScreenComponentImpl

class ProfileComponentImpl(
    componentContext: ComponentContext,
) : ProfileComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<ProfileConfiguration>()

    override val stack: Value<ChildStack<ProfileConfiguration, ProfileComponent.ProfileChild>> =
        childStack(
            source = navigation,
            serializer = ProfileConfiguration.serializer(),
            initialConfiguration = ProfileConfiguration.Profile,
            handleBackButton = true,
            childFactory = ::createFavoriteChild
        )

    override fun onBack() {
        navigation.pop()
    }

    override fun onNavigation(screen: ProfileConfiguration) {
        navigation.pushNew(screen)
    }

    private fun createFavoriteChild(
        config: ProfileConfiguration,
        childComponentContext: ComponentContext
    ): ProfileComponent.ProfileChild = when (config) {

        ProfileConfiguration.Profile ->  ProfileComponent.ProfileChild.Profile(
            component = ProfileScreenComponentImpl(
                componentContext = childComponentContext,
                mainContext = Dispatchers.Main,
                onNavigation = { profileConfiguration ->
                    onNavigation(profileConfiguration)
                },
            )
        )

    }

}
