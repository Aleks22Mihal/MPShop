package mpshop.app.presentation.utils

sealed class LoadState(val errorMessage: String? = null){
    data object Successful: LoadState()
    data object Loading: LoadState()
    class Error(textError: String): LoadState(errorMessage = textError)
}