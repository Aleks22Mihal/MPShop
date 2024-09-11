package mpshop.app.presentation.navigation.shoppingCartNavigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import mpshop.app.presentation.screens.productScreen.ProductScreen
import mpshop.app.presentation.screens.shoppingCartScreen.ShoppingCartScreen

@Composable
fun ShoppingCart(shoppingCartComponent: ShoppingCartComponent) {

    val childStack by shoppingCartComponent.stack.subscribeAsState()

    Children(
        stack = childStack,
        animation = stackAnimation(slide()),
    ) { child ->
        when (val instance = child.instance) {
            is ShoppingCartComponent.ShoppingCartChild.ShoppingCart -> ShoppingCartScreen(instance.component)
            is ShoppingCartComponent.ShoppingCartChild.Product -> ProductScreen(instance.component)
        }
    }
}