package mpshop.app.presentation.navigation.profileNavigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import mpshop.app.presentation.screens.profileScreen.ProfileScreen

@Composable
fun Profile(profileComponent: ProfileComponent) {

    val childStack by profileComponent.stack.subscribeAsState()

    Children(
        stack = childStack,
        animation = stackAnimation(slide()),
    ) { child ->
        when (val instance = child.instance) {
            is ProfileComponent.ProfileChild.Profile -> ProfileScreen(instance.component)
        }
    }
}