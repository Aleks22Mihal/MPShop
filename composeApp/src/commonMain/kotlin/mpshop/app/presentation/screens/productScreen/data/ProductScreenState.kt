package mpshop.app.presentation.screens.productScreen.data

import mpshop.app.presentation.models.Product
import mpshop.app.presentation.utils.LoadState

data class ProductScreenState(
    val product: Product? = null,
    val selectSizeIndex: Int = 0,
    val selectColorIndex: Int = 0,
    val countProduct: Int = 1,
    val showTypeBottomSheet: ModalBottomSheetType? = null,
    val loadState: LoadState = LoadState.Successful,
)