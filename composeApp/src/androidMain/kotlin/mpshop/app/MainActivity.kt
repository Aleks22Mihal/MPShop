package mpshop.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import com.arkivanov.decompose.retainedComponent
import com.arkivanov.essenty.backhandler.BackDispatcher
import mpshop.app.presentation.navigation.rootNavigation.DefaultRootComponent

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        val rootComponent = retainedComponent { componentContext ->

            DefaultRootComponent(componentContext = componentContext)
        }

        setContent {
            App(
                rootComponent = rootComponent,
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = false,
            )
        }
    }
}
