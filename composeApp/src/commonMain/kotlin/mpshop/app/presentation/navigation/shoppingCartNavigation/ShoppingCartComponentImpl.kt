package mpshop.app.presentation.navigation.shoppingCartNavigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.Dispatchers
import mpshop.app.presentation.screens.productScreen.ProductScreenComponentImpl
import mpshop.app.presentation.screens.shoppingCartScreen.ShoppingCartScreenComponentImpl

class ShoppingCartComponentImpl(
    componentContext: ComponentContext,
) : ShoppingCartComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<ShoppingCartConfiguration>()

    override val stack: Value<ChildStack<ShoppingCartConfiguration, ShoppingCartComponent.ShoppingCartChild>> =
        childStack(
            source = navigation,
            serializer = ShoppingCartConfiguration.serializer(),
            initialConfiguration = ShoppingCartConfiguration.ShoppingCart,
            handleBackButton = true,
            childFactory = ::createFavoriteChild
        )

    override fun onBack() {
        navigation.pop()
    }

    override fun onNavigation(screen: ShoppingCartConfiguration) {
        navigation.pushNew(screen)
    }

    private fun createFavoriteChild(
        config: ShoppingCartConfiguration,
        childComponentContext: ComponentContext
    ): ShoppingCartComponent.ShoppingCartChild = when (config) {

        ShoppingCartConfiguration.ShoppingCart ->  ShoppingCartComponent.ShoppingCartChild.ShoppingCart(
            component = ShoppingCartScreenComponentImpl(
                componentContext = childComponentContext,
                mainContext = Dispatchers.Main,
                onNavigation = { shoppingCartConfiguration ->
                    onNavigation(shoppingCartConfiguration)
                },
            )
        )

        is ShoppingCartConfiguration.Product -> ShoppingCartComponent.ShoppingCartChild.Product(
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
