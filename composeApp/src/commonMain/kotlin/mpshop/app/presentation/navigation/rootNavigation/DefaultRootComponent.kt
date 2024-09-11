package mpshop.app.presentation.navigation.rootNavigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackDispatcher
import com.arkivanov.essenty.backhandler.BackHandler
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import mpshop.app.presentation.navigation.favoriteNavigation.FavoriteComponentImpl
import mpshop.app.presentation.navigation.homeNavigation.HomeComponentImpl
import mpshop.app.presentation.navigation.profileNavigation.ProfileComponentImpl
import mpshop.app.presentation.navigation.rootNavigation.RootComponent.Child
import mpshop.app.presentation.navigation.shoppingCartNavigation.ShoppingCartComponentImpl

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()

    override val stack: Value<ChildStack<*, Child>> = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.Home,
        handleBackButton = true,
        childFactory = ::child,
    )



    override fun onBackClicked() {
        navigation.pop()
    }

    override fun onNavItemInBottomBar(screen: Configuration) {
        navigation.bringToFront(screen)
    }

    private fun child(
        config: Configuration,
        childComponentContext: ComponentContext
    ): Child = when (config) {

        is Configuration.Home -> Child.Home(
            component = HomeComponentImpl(
                componentContext = childComponentContext,
            )
        )

        is Configuration.ShoppingCart -> Child.ShoppingCart(
            component = ShoppingCartComponentImpl(
                componentContext = childComponentContext
            )
        )

        Configuration.Favorite -> Child.Favorite(
            component = FavoriteComponentImpl(
                componentContext = childComponentContext
            )
        )

        Configuration.Profile -> Child.Profile(
            component = ProfileComponentImpl(
                componentContext = childComponentContext
            )
        )
    }


}
