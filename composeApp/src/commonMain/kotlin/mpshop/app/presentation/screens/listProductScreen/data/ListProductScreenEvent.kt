package mpshop.app.presentation.screens.listProductScreen.data

sealed class ListProductScreenEvent {
    data object Refresh: ListProductScreenEvent()
    data class OnClickProduct(val productId: String) : ListProductScreenEvent()
    data object OnClickBack: ListProductScreenEvent()
}