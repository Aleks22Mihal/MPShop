package mpshop.app.presentation.screens.homeScreen.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mpshop.app.presentation.screens.homeScreen.data.HomeScreenEvent
import mpshop.app.presentation.screens.homeScreen.data.HomeScreenState

@Composable
fun CategorySelectionView(
    state: HomeScreenState,
    onEvent: (HomeScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Text(
            text = "Categories",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        TextButton(
            onClick = {
                onEvent(HomeScreenEvent.OnClickCategory)
            }
        ) {
            Text(
                text = "All",
                fontSize = 16.sp,
            )
        }
    }

    LazyRow(
        contentPadding = PaddingValues(start = 8.dp, end = 8.dp),
    ) {
        items(state.categories) { category ->
            CategoryItemView(
                category = category,
                onEvent = onEvent,
            )
        }
    }

}