package mpshop.app.presentation.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import mpshop.app.presentation.navigation.RootComponent.*
import mpshop.app.presentation.screens.catalogScreen.CatalogScreenComponent
import mpshop.app.presentation.screens.homeScreen.HomeScreenComponent

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Home,
        handleBackButton = true,
        childFactory = ::child,
    )

    override fun onBackClicked(toIndex: Int) {
        navigation.pop()
    }

    private fun child(
        config: Config,
        childComponentContext: ComponentContext
    ): Child = when (config) {

        is Config.Home -> Child.Home(
            component = HomeScreenComponent(
                componentContext = childComponentContext
            )
        )

        is Config.Catalog -> Child.Catalog(
            component = CatalogScreenComponent(
                componentContext = childComponentContext
            )
        )
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object Home : Config

        @Serializable
        data object Catalog : Config
    }
}
