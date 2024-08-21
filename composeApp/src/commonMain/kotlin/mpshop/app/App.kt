package mpshop.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import mpshop.app.presentation.navigation.RootComponent
import mpshop.app.presentation.screens.catalogScreen.CatalogScreen
import mpshop.app.presentation.screens.homeScreen.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    rootComponent: RootComponent
) {

    val childStack by rootComponent.stack.subscribeAsState()

    Children(
        stack = childStack,
        animation = stackAnimation(slide()),
    ) { child ->
        when (val instance = child.instance) {

            is RootComponent.Child.Home -> HomeScreen(instance.component)

            is RootComponent.Child.Catalog -> CatalogScreen(instance.component)
        }
    }
}