package mpshop.app.presentation.screens.profileScreen

import com.arkivanov.decompose.value.Value
import mpshop.app.presentation.screens.profileScreen.data.ProfileScreenEvent
import mpshop.app.presentation.screens.profileScreen.data.ProfileScreenState

interface ProfileScreenComponent {
    val state: Value<ProfileScreenState>

    fun onEvent(event: ProfileScreenEvent)
}