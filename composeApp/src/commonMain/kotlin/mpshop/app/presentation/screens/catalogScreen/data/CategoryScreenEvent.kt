package mpshop.app.presentation.screens.catalogScreen.data

sealed class CategoryScreenEvent {

    data object Refresh : CategoryScreenEvent()

    data object OnClickBack : CategoryScreenEvent()

    data class OnClickCategory(
        val nameCategory: String,
        val categoryId: Int
    ) : CategoryScreenEvent()
}