package mpshop.app.presentation.screens.listProductScreen

import com.arkivanov.decompose.value.Value
import mpshop.app.presentation.screens.listProductScreen.data.ListProductScreenEvent
import mpshop.app.presentation.screens.listProductScreen.data.ListProductScreenState

interface ListProductScreenComponent {

    val state: Value<ListProductScreenState>

    fun onEvent(event: ListProductScreenEvent)

}