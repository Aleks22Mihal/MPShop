package mpshop.app.presentation.screens.productScreen.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mpshop.app.presentation.screens.productScreen.data.ProductScreenEvent
import mpshop.app.presentation.screens.productScreen.data.ProductScreenState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SizeContentBottomSheetView(
    state: ProductScreenState,
    onEvent: (ProductScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        state.product?.size?.forEachIndexed { index, size ->
            Card(
                onClick = {
                    onEvent(ProductScreenEvent.SelectSize(index))
                },
                colors = CardDefaults.cardColors(
                    containerColor = if (index == state.selectSizeIndex) {
                        MaterialTheme.colorScheme.primary
                    } else MaterialTheme.colorScheme.surfaceContainerHigh
                ),
                modifier = Modifier
                    .size(50.dp)
                    .padding(4.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = size,
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}