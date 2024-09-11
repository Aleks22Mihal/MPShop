package mpshop.app.presentation.navigation.favoriteNavigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.Dispatchers
import mpshop.app.presentation.screens.favoriteScreen.FavoriteScreenComponentImpl
import mpshop.app.presentation.screens.productScreen.ProductScreenComponentImpl

class FavoriteComponentImpl(
    componentContext: ComponentContext,
) : FavoriteComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<FavoriteConfiguration>()

    override val stack: Value<ChildStack<FavoriteConfiguration, FavoriteComponent.FavoriteChild>> =
        childStack(
            source = navigation,
            serializer = FavoriteConfiguration.serializer(),
            initialConfiguration = FavoriteConfiguration.Favorite,
            handleBackButton = true,
            childFactory = ::createFavoriteChild
        )

    override fun onBack() {
        navigation.pop()
    }

    override fun onNavigation(screen: FavoriteConfiguration) {
        navigation.pushNew(screen)
    }

    private fun createFavoriteChild(
        config: FavoriteConfiguration,
        childComponentContext: ComponentContext
    ): FavoriteComponent.FavoriteChild = when (config) {

        FavoriteConfiguration.Favorite ->  FavoriteComponent.FavoriteChild.Favorite(
            component = FavoriteScreenComponentImpl(
                componentContext = childComponentContext,
                mainContext = Dispatchers.Main,
                onNavigation = { favoriteConfiguration ->
                    onNavigation(favoriteConfiguration)
                },
            )
        )

        is FavoriteConfiguration.Product -> FavoriteComponent.FavoriteChild.Product(
            component = ProductScreenComponentImpl(
                componentContext = childComponentContext,
                mainContext = Dispatchers.Main,
                onBack = {
                    onBack()
                },
                productId = config.productId
            ),
        )
    }
}
