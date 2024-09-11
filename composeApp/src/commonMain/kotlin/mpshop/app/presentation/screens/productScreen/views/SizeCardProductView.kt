package mpshop.app.presentation.screens.productScreen.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mpshop.app.presentation.screens.productScreen.data.ModalBottomSheetType
import mpshop.app.presentation.screens.productScreen.data.ProductScreenEvent
import mpshop.app.presentation.screens.productScreen.data.ProductScreenState
import mpshop.composeapp.generated.resources.Res
import mpshop.composeapp.generated.resources.arrow_down_icon_24_dp
import org.jetbrains.compose.resources.painterResource

@Composable
fun SizeCardProductView(
    state: ProductScreenState,
    onEvent: (ProductScreenEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = {
            onEvent(
                ProductScreenEvent.ShowBottomSheet(
                    ModalBottomSheetType.Size
                )
            )
        },
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Size",
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
            ) {
                Text(
                    text = try {
                        state.product?.size?.get(state.selectSizeIndex) ?: ""
                    } catch (e: Exception) {
                        ""
                    },
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(end = 16.dp)
                )
                Icon(
                    painter = painterResource(Res.drawable.arrow_down_icon_24_dp),
                    contentDescription = "Select size"
                )
            }
        }
    }
}