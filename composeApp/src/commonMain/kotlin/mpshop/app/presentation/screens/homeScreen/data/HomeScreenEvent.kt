package mpshop.app.presentation.screens.homeScreen.data

import mpshop.app.presentation.models.Product
import mpshop.app.presentation.screens.productScreen.data.ModalBottomSheetType
import mpshop.app.presentation.screens.productScreen.data.ProductScreenEvent

sealed class HomeScreenEvent {
    data object Refresh : HomeScreenEvent()

    data object OnClickCategory : HomeScreenEvent()

    data class OnClickProduct(val productId: String) : HomeScreenEvent()

    data class OnClickListProduct(
        val categoryId: Int? = null,
        val nameCategory: String
    ) : HomeScreenEvent()

    data class ChangeSearchText(val text: String) : HomeScreenEvent()

    data class AddInFavorite(val product: Product) : HomeScreenEvent()

    data class DeleteInFavorite(val productId: Int) : HomeScreenEvent()

    data class ChangeSearchBarText(val queryText: String) : HomeScreenEvent()

    data class ShowBottomSheet(val isShowType: ModalBottomSheetType?) : HomeScreenEvent()

    data object ActiveChangeSearchBar : HomeScreenEvent()
}