package mpshop.app.presentation.screens.catalogScreen.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mpshop.app.presentation.appComponents.ImageAsyncLoad
import mpshop.app.presentation.models.Category
import mpshop.app.presentation.screens.catalogScreen.data.CategoryScreenEvent

@Composable
fun CardCategoryView(
    category: Category,
    onEvent: (CategoryScreenEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = {
            onEvent(
                CategoryScreenEvent.OnClickCategory(
                    nameCategory = category.name,
                    categoryId = category.id
                )
            )
        },
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            ImageAsyncLoad(
                data = category.image,
                contentDescription = "Category Image",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            Text(
                text = category.name,
                fontSize = 18.sp
            )
        }
    }
}