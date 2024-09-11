package mpshop.app.presentation.navigation.rootNavigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandler
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import mpshop.app.presentation.navigation.favoriteNavigation.FavoriteComponent
import mpshop.app.presentation.navigation.homeNavigation.HomeComponent
import mpshop.app.presentation.navigation.profileNavigation.ProfileComponent
import mpshop.app.presentation.navigation.shoppingCartNavigation.ShoppingCartComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    fun onBackClicked()

    fun onNavItemInBottomBar(screen: Configuration)

    sealed class Child {
        class Home(val component: HomeComponent) : Child()
        class ShoppingCart(val component: ShoppingCartComponent) : Child()
        class Favorite(val component: FavoriteComponent) : Child()
        class Profile(val component: ProfileComponent) : Child()
    }
}