package mpshop.app.presentation.navigation.shoppingCartNavigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import mpshop.app.presentation.screens.productScreen.ProductScreenComponent
import mpshop.app.presentation.screens.shoppingCartScreen.ShoppingCartScreenComponent

interface ShoppingCartComponent {
    val stack: Value<ChildStack<ShoppingCartConfiguration, ShoppingCartChild>>

    fun onBack()

    fun onNavigation(screen: ShoppingCartConfiguration)

    sealed class ShoppingCartChild {
        class ShoppingCart(val component: ShoppingCartScreenComponent) : ShoppingCartChild()
        class Product(val component: ProductScreenComponent) : ShoppingCartChild()
    }
}