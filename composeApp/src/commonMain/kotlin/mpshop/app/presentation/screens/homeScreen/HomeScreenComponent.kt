package mpshop.app.presentation.screens.homeScreen

import com.arkivanov.decompose.value.Value
import mpshop.app.presentation.screens.homeScreen.data.HomeScreenEvent
import mpshop.app.presentation.screens.homeScreen.data.HomeScreenState

interface HomeScreenComponent {

    val state: Value<HomeScreenState>

    fun onEvent(event: HomeScreenEvent)

}