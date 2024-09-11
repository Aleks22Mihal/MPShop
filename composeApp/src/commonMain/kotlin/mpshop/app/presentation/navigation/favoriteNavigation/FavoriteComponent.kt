package mpshop.app.presentation.navigation.favoriteNavigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import mpshop.app.presentation.screens.favoriteScreen.FavoriteScreenComponent
import mpshop.app.presentation.screens.productScreen.ProductScreenComponent

interface FavoriteComponent {
    val stack: Value<ChildStack<FavoriteConfiguration, FavoriteChild>>

    fun onBack()

    fun onNavigation(screen: FavoriteConfiguration)

    sealed class FavoriteChild {
        class Favorite(val component: FavoriteScreenComponent) : FavoriteChild()
        class Product(val component: ProductScreenComponent) : FavoriteChild()
    }
}