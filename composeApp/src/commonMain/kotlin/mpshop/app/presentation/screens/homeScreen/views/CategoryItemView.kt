package mpshop.app.presentation.screens.homeScreen.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mpshop.app.presentation.appComponents.ImageAsyncLoad
import mpshop.app.presentation.models.Category
import mpshop.app.presentation.screens.homeScreen.data.HomeScreenEvent

@Composable
fun CategoryItemView(
    category: Category,
    onEvent: (HomeScreenEvent) -> Unit
) {
    Card(
        onClick = {
            onEvent(
                HomeScreenEvent.OnClickListProduct(
                    nameCategory = category.name,
                    categoryId = category.id
                )
            )
        },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        modifier = Modifier
            .width(80.dp)
            .height(80.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ImageAsyncLoad(
                data = category.image,
                contentDescription = null,
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(100))
            )

            Text(
                text = category.name,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .height(19.dp)
                    .fillMaxWidth()
            )
        }
    }
}
