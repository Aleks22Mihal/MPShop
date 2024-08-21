package mpshop.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.retainedComponent
import mpshop.app.presentation.navigation.DefaultRootComponent

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        val rootComponent = retainedComponent { componentContext ->
            DefaultRootComponent(componentContext)
        }

        setContent {
            App(rootComponent = rootComponent)
        }
    }
}
