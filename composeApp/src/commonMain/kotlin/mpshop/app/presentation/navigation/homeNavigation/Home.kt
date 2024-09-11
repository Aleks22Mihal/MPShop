package mpshop.app.presentation.navigation.homeNavigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import mpshop.app.presentation.screens.catalogScreen.CategoryScreen
import mpshop.app.presentation.screens.homeScreen.HomeScreen
import mpshop.app.presentation.screens.listProductScreen.ListProductScreen
import mpshop.app.presentation.screens.productScreen.ProductScreen

@Composable
fun Home(homeScreenComponent: HomeComponent){

    val childStack by homeScreenComponent.stack.subscribeAsState()

    Children(
        stack = childStack,
        animation = stackAnimation(slide()),
    ) { child ->
        when (val instance = child.instance) {
            is HomeComponent.HomeChild.Category -> CategoryScreen(instance.component)
            is HomeComponent.HomeChild.Home -> HomeScreen(instance.component)
            is HomeComponent.HomeChild.ListProduct -> ListProductScreen(instance.component)
            is HomeComponent.HomeChild.Product -> ProductScreen(instance.component)
        }
    }
}