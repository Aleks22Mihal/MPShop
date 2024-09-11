package mpshop.app.presentation.screens.homeScreen.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mpshop.app.presentation.appComponents.CardProductView
import mpshop.app.presentation.models.Product
import mpshop.app.presentation.screens.homeScreen.data.HomeScreenEvent
import mpshop.composeapp.generated.resources.Res
import mpshop.composeapp.generated.resources.arrow_right_icon_16dp
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProductSelectionView(
    productSelectionName: String,
    productSelectionItem: List<Product>,
    modifier: Modifier = Modifier,
    onEvent: (HomeScreenEvent) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = productSelectionName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            TextButton(
                onClick = {
                    onEvent(
                        HomeScreenEvent.OnClickListProduct(
                            nameCategory = productSelectionName
                        )
                    )
                }
            ) {
                Text(
                    text = "All",
                    fontSize = 16.sp,
                )
            }
        }

        LazyRow(
            contentPadding = PaddingValues(
                start = 8.dp,
                end = 8.dp
            ),
        ) {
            items(productSelectionItem) { prodItem ->
                CardProductView(
                    product = prodItem,
                    onClick = {
                        onEvent(
                            HomeScreenEvent.OnClickProduct(
                                productId = prodItem.id.toString()
                            )
                        )
                    },
                    onClickFavorite = {
                        onEvent(
                            HomeScreenEvent.AddInFavorite(
                                product = prodItem
                            )
                        )
                    },
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )
            }
            item {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(280.dp)
                        .width(160.dp)
                        .padding(
                            start = 8.dp,
                            end = 8.dp
                        )
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(
                            onClick = {
                                onEvent(
                                    HomeScreenEvent.OnClickListProduct(
                                        nameCategory = productSelectionName
                                    )
                                )
                            },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.arrow_right_icon_16dp),
                                contentDescription = "Next"
                            )
                        }
                        Text(
                            text = "All",
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp,
                        )
                    }
                }
            }
        }
    }
}