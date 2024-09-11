package mpshop.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.PredictiveBackGestureIcon
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.PredictiveBackGestureOverlay
import com.arkivanov.essenty.backhandler.BackDispatcher
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import mpshop.app.di.initKoin
import mpshop.app.presentation.navigation.rootNavigation.DefaultRootComponent
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

@OptIn(ExperimentalDecomposeApi::class)
fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) {

    val backDispatcher = BackDispatcher()

    val rootComponent = remember {
        DefaultRootComponent(
            componentContext = DefaultComponentContext(
                lifecycle = LifecycleRegistry(),
                backHandler = backDispatcher
            )
        )
    }
    val isDarkTheme =
        UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
                UIUserInterfaceStyle.UIUserInterfaceStyleDark

    PredictiveBackGestureOverlay(
        backDispatcher = backDispatcher,
        backIcon = { progress, _ ->
            PredictiveBackGestureIcon(
                imageVector = Icons.Default.ArrowBack,
                progress = progress,
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) {
        App(
            rootComponent = rootComponent,
            darkTheme = isDarkTheme,
            dynamicColor = false
        )
    }
}