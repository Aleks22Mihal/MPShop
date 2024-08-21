package mpshop.app

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import mpshop.app.presentation.navigation.DefaultRootComponent

fun MainViewController() = ComposeUIViewController {

    val rootComponent = remember {
        DefaultRootComponent(DefaultComponentContext(LifecycleRegistry()))
    }

    App(
        rootComponent = rootComponent
    )
}