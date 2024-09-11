package mpshop.app.presentation.screens.favoriteScreen

import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.StateFlow
import mpshop.app.presentation.screens.favoriteScreen.data.FavoriteScreenEvent
import mpshop.app.presentation.screens.favoriteScreen.data.FavoriteScreenState

interface FavoriteScreenComponent {
    val state: Value<FavoriteScreenState>

    fun onEvent(event: FavoriteScreenEvent)
}