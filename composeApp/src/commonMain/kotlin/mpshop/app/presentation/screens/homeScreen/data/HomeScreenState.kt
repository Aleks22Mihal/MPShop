package mpshop.app.presentation.screens.homeScreen.data

import mpshop.app.presentation.models.Category
import mpshop.app.presentation.models.Product
import mpshop.app.presentation.screens.productScreen.data.ModalBottomSheetType
import mpshop.app.presentation.utils.LoadState

data class HomeScreenState(
    val loadState: LoadState = LoadState.Successful,
    val categories: List<Category> = emptyList(),
    val topProducts: List<Product> = emptyList(),
    val saleProducts: List<Product> = emptyList(),
    val newCollectionProducts: List<Product> = emptyList(),
    val searchListProducts: List<Product> = emptyList(),
    val searchBarText: String = "",
    val isActiveSearchBar: Boolean = false,
    val showBottomSheet: ModalBottomSheetType? = null,
    val loadStateAddInFavorite: LoadState = LoadState.Successful,
)
