package mpshop.app.presentation.navigation.favoriteNavigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import mpshop.app.presentation.screens.favoriteScreen.FavoriteScreen
import mpshop.app.presentation.screens.productScreen.ProductScreen

@Composable
fun Favorite(favoriteComponent: FavoriteComponent) {

    val childStack by favoriteComponent.stack.subscribeAsState()

    Children(
        stack = childStack,
        animation = stackAnimation(slide()),
    ) { child ->
        when (val instance = child.instance) {
            is FavoriteComponent.FavoriteChild.Favorite -> FavoriteScreen(instance.component)
            is FavoriteComponent.FavoriteChild.Product -> ProductScreen(instance.component)
        }
    }
}