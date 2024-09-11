package mpshop.app.presentation.screens.profileScreen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import mpshop.app.presentation.navigation.profileNavigation.ProfileConfiguration
import mpshop.app.presentation.screens.profileScreen.data.ProfileScreenEvent
import mpshop.app.presentation.screens.profileScreen.data.ProfileScreenState
import org.koin.core.component.KoinComponent
import kotlin.coroutines.CoroutineContext

class ProfileScreenComponentImpl(
    componentContext: ComponentContext,
    mainContext: CoroutineContext,
    private val onNavigation: (ProfileConfiguration) -> Unit,
) : ProfileScreenComponent, ComponentContext by componentContext, KoinComponent {

    private val _state = MutableValue(ProfileScreenState())
    override val state: Value<ProfileScreenState> = _state

    init {
        init()
    }

    override fun onEvent(event: ProfileScreenEvent) {
        when (event) {

            ProfileScreenEvent.Refresh -> init()

        }

    }

    private fun init() {

    }
}