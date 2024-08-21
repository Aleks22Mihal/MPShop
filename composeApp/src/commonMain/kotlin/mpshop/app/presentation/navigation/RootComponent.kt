package mpshop.app.presentation.navigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import mpshop.app.presentation.screens.catalogScreen.CatalogScreenComponent
import mpshop.app.presentation.screens.homeScreen.HomeScreenComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    fun onBackClicked(toIndex: Int)

    sealed class Child {
        class Home(val component: HomeScreenComponent) : Child()
        class Catalog(val component: CatalogScreenComponent) : Child()
    }
}