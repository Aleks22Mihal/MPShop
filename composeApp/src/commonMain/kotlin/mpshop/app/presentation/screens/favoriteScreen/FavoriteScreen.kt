package mpshop.app.presentation.screens.favoriteScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState

@Composable
fun FavoriteScreen(favoriteScreenComponent: FavoriteScreenComponent) {

    val state by favoriteScreenComponent.state.subscribeAsState()

    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(state.listProducts) { product ->
            Text(product.title)

        }
    }
}