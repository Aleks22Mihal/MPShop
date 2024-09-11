package mpshop.app.presentation.navigation.homeNavigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackDispatcher
import com.arkivanov.essenty.backhandler.BackHandler
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import kotlinx.coroutines.Dispatchers
import mpshop.app.presentation.screens.catalogScreen.CategoryScreenComponentImpl
import mpshop.app.presentation.screens.homeScreen.HomeScreenComponentImpl
import mpshop.app.presentation.screens.listProductScreen.ListProductScreenComponentImpl
import mpshop.app.presentation.screens.productScreen.ProductScreenComponentImpl

class HomeComponentImpl(
    componentContext: ComponentContext,
) : HomeComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<HomeConfiguration>()

    override val stack: Value<ChildStack<HomeConfiguration, HomeComponent.HomeChild>> = childStack(
        source = navigation,
        serializer = HomeConfiguration.serializer(),
        initialConfiguration = HomeConfiguration.Home,
        handleBackButton = true,
        childFactory = ::createHomeChild
    )

    override fun onBackClicked() {
        navigation.pop()
    }

    override fun onNavigation(screen: HomeConfiguration) {
        navigation.pushNew(screen)
    }

    private fun createHomeChild(
        config: HomeConfiguration,
        childComponentContext: ComponentContext
    ): HomeComponent.HomeChild = when (config) {

        HomeConfiguration.Category -> HomeComponent.HomeChild.Category(
            component = CategoryScreenComponentImpl(
                componentContext = childComponentContext,
                mainContext = Dispatchers.Main,
                onNavigation = { homeConfiguration ->
                    onNavigation(homeConfiguration)
                },
                onBack = {
                    onBackClicked()
                },
            )
        )

        HomeConfiguration.Home -> HomeComponent.HomeChild.Home(
            component = HomeScreenComponentImpl(
                componentContext = childComponentContext,
                mainContext = Dispatchers.Main,
                onNavigation = { homeConfiguration ->
                    onNavigation(homeConfiguration)
                }
            )
        )

        is HomeConfiguration.ListProduct -> HomeComponent.HomeChild.ListProduct(
            component = ListProductScreenComponentImpl(
                componentContext = childComponentContext,
                mainContext = Dispatchers.Main,
                onBack = {
                    onBackClicked()
                },
                onNavigation = { homeConfiguration ->
                    onNavigation(homeConfiguration)
                },
                nameCategory = config.nameCategory,
                categoryId = config.categoryId
            ),
        )

        is HomeConfiguration.Product -> HomeComponent.HomeChild.Product(
            component = ProductScreenComponentImpl(
                componentContext = childComponentContext,
                mainContext = Dispatchers.Main,
                onBack = {
                    onBackClicked()
                },
                productId = config.productId
            ),
        )
    }
}
