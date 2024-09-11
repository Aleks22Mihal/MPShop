package mpshop.app.presentation.navigation.rootNavigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import mpshop.app.presentation.appComponents.BottomBarNavigation
import mpshop.app.presentation.navigation.favoriteNavigation.Favorite
import mpshop.app.presentation.navigation.homeNavigation.Home
import mpshop.app.presentation.navigation.profileNavigation.Profile
import mpshop.app.presentation.navigation.shoppingCartNavigation.ShoppingCart

@Composable
fun Root(rootComponent: RootComponent) {

    val childStack by rootComponent.stack.subscribeAsState()

    Scaffold(
        bottomBar = {
            BottomBarNavigation(
                currentInstance = childStack.active.instance,
                onItemSelected = rootComponent::onNavItemInBottomBar
            )
        }
    ) { innerPadding ->
        Children(
            stack = childStack,
            animation = stackAnimation(fade()),
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
        ) { child ->
            when (val instance = child.instance) {

                is RootComponent.Child.Home -> Home(instance.component)

                is RootComponent.Child.ShoppingCart -> ShoppingCart(instance.component)

                is RootComponent.Child.Favorite -> Favorite(instance.component)

                is RootComponent.Child.Profile -> Profile(instance.component)
            }
        }
    }

}