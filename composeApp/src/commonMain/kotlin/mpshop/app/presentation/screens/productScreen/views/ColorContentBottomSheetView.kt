package mpshop.app.presentation.screens.productScreen.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import mpshop.app.presentation.screens.productScreen.data.ProductScreenEvent
import mpshop.app.presentation.screens.productScreen.data.ProductScreenState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ColorContentBottomSheetView(
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
        state.product?.colors?.forEachIndexed { index, color ->
            Card(
                onClick = {
                    onEvent(ProductScreenEvent.SelectColor(index))
                },
                colors = CardDefaults.cardColors(
                    containerColor = if (index == state.selectColorIndex) {
                        MaterialTheme.colorScheme.primary
                    } else MaterialTheme.colorScheme.surfaceContainerHigh
                ),
                modifier = Modifier
                    .size(50.dp)
                    .padding(4.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .border(
                                width = 4.dp,
                                shape = CircleShape,
                                color = if (index == state.selectColorIndex) {
                                    Color.White
                                } else Color.Transparent
                            )
                            .size(25.dp)
                            .background(color)
                    )
                }
            }
        }
    }
}