package mpshop.app.presentation.screens.profileScreen.data

sealed class ProfileScreenEvent {

    data object Refresh: ProfileScreenEvent()
}