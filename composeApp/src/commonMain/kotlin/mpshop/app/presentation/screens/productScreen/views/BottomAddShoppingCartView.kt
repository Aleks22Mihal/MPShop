package mpshop.app.presentation.screens.productScreen.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mpshop.app.presentation.screens.productScreen.data.ProductScreenEvent
import mpshop.app.presentation.screens.productScreen.data.ProductScreenState
import mpshop.composeapp.generated.resources.Res
import mpshop.composeapp.generated.resources.shopping_cart_icon_24dp
import org.jetbrains.compose.resources.painterResource

@Composable
fun BottomAddShoppingCartView(
    state: ProductScreenState,
    onEvent: (ProductScreenEvent) -> Unit,
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.background
            )
            .padding(horizontal = 16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Total Price",
                fontSize = 9.sp,
                fontWeight = FontWeight.Light,
                lineHeight = 9.sp
            )
            Text(
                text = "${(state.product?.price ?: 0) * state.countProduct}$",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 18.sp
            )
        }
        Button(onClick = {
            onEvent(ProductScreenEvent.AddShoppingCart)
        }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    8.dp,
                    Alignment.CenterHorizontally
                ),
            ) {
                Icon(
                    painter = painterResource(Res.drawable.shopping_cart_icon_24dp),
                    contentDescription = "Add to cart"
                )
                Text(
                    text = "Add to cart",
                    fontSize = 18.sp,
                )
            }
        }
    }

}