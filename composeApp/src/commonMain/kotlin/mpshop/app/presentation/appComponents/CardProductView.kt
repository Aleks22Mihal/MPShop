package mpshop.app.presentation.appComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mpshop.app.presentation.models.Product
import mpshop.composeapp.generated.resources.Res
import mpshop.composeapp.generated.resources.heart_icon_16dp
import org.jetbrains.compose.resources.painterResource

@Composable
fun CardProductView(
    product: Product,
    onClickFavorite: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = {
            onClick()
        },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
        ),
        modifier = modifier
            .height(280.dp)
            .width(160.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .height(220.dp)
                    .fillMaxWidth()
                    .padding(4.dp)
                    .clip(MaterialTheme.shapes.medium)
            ) {
                ImageAsyncLoad(
                    data = product.images[0],
                    contentDescription = product.title,
                )
                IconButton(
                    onClick = {
                        onClickFavorite()
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.heart_icon_16dp),
                        contentDescription = "Like"
                    )
                }
            }

            Text(
                text = product.title,
                fontSize = 12.sp,
                maxLines = 1,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 4.dp,
                        end = 4.dp
                    )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "$${product.price}",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                maxLines = 2,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, end = 4.dp)
            )
        }
    }
}
