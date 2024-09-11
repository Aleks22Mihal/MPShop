package mpshop.app.presentation.screens.productScreen.data

sealed class ProductScreenEvent {
    data object Refresh : ProductScreenEvent()
    data object OnClickBack : ProductScreenEvent()
    data object CountProductPlus : ProductScreenEvent()
    data object CountProductMinus : ProductScreenEvent()
    data class SelectSize(val sizeIndex: Int) : ProductScreenEvent()
    data class SelectColor(val colorIndex: Int) : ProductScreenEvent()
    data class ShowBottomSheet(val isShowType: ModalBottomSheetType?) : ProductScreenEvent()
    data object AddShoppingCart : ProductScreenEvent()
}