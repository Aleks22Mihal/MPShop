package mpshop.app.presentation.screens.productScreen

import com.arkivanov.decompose.value.Value
import mpshop.app.presentation.screens.productScreen.data.ProductScreenEvent
import mpshop.app.presentation.screens.productScreen.data.ProductScreenState

interface ProductScreenComponent {
    val state: Value<ProductScreenState>

    fun onEvent(event: ProductScreenEvent)
}