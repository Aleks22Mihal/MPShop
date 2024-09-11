package mpshop.app

import androidx.compose.runtime.Composable
import mpshop.app.presentation.navigation.rootNavigation.Root
import mpshop.app.presentation.navigation.rootNavigation.RootComponent
import mpshop.app.presentation.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    rootComponent: RootComponent,
    darkTheme: Boolean,
    dynamicColor: Boolean,
) {
    AppTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
        Root(rootComponent)
    }
}
