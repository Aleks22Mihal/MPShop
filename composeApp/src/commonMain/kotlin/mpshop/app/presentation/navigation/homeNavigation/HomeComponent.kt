package mpshop.app.presentation.navigation.homeNavigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import mpshop.app.presentation.screens.catalogScreen.CategoryScreenComponent
import mpshop.app.presentation.screens.homeScreen.HomeScreenComponent
import mpshop.app.presentation.screens.listProductScreen.ListProductScreenComponent
import mpshop.app.presentation.screens.productScreen.ProductScreenComponent

interface HomeComponent {
    val stack: Value<ChildStack<HomeConfiguration, HomeChild>>

    fun onBackClicked()

    fun onNavigation(screen: HomeConfiguration)

    sealed class HomeChild {
        class Home(val component: HomeScreenComponent) : HomeChild()
        class Category(val component: CategoryScreenComponent) : HomeChild()
        class ListProduct(val component: ListProductScreenComponent) : HomeChild()
        class Product(val component: ProductScreenComponent) : HomeChild()
    }

}