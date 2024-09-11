package mpshop.app.presentation.screens.catalogScreen.data

import mpshop.app.presentation.models.Category
import mpshop.app.presentation.utils.LoadState

data class CategoryScreenState(
    val listCategories: List<Category> = emptyList(),
    val loadState: LoadState = LoadState.Successful,
)
