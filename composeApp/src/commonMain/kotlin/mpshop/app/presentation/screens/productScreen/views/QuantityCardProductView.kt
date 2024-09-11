package mpshop.app.presentation.screens.productScreen.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mpshop.app.presentation.screens.productScreen.data.ProductScreenEvent
import mpshop.app.presentation.screens.productScreen.data.ProductScreenState
import mpshop.composeapp.generated.resources.Res
import mpshop.composeapp.generated.resources.icon_minus_16dp
import mpshop.composeapp.generated.resources.icon_plus_16dp
import org.jetbrains.compose.resources.painterResource

@Composable
fun QuantityCardProductView(
    state: ProductScreenState,
    onEvent: (ProductScreenEvent) -> Unit,
    modifier: Modifier = Modifier,

) {
    Card(
        modifier = modifier
            .height(56.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Quantity",
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            ) {
                IconButton(
                    onClick = {
                        onEvent(ProductScreenEvent.CountProductPlus)
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.icon_plus_16dp),
                        contentDescription = "plus",
                        tint = Color.White
                    )
                }
                Text(
                    text = state.countProduct.toString(),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
                IconButton(
                    onClick = {
                        onEvent(ProductScreenEvent.CountProductMinus)
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.icon_minus_16dp),
                        contentDescription = "minus",
                        tint = Color.White
                    )
                }
            }
        }
    }
}