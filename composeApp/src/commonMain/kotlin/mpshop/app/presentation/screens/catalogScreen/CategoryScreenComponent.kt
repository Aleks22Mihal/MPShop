package mpshop.app.presentation.screens.catalogScreen

import com.arkivanov.decompose.value.Value
import mpshop.app.presentation.screens.catalogScreen.data.CategoryScreenEvent
import mpshop.app.presentation.screens.catalogScreen.data.CategoryScreenState

interface CategoryScreenComponent {
    val state: Value<CategoryScreenState>

    fun onEvent(event: CategoryScreenEvent)
}