package mpshop.app.presentation.screens.productScreen.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import mpshop.app.presentation.screens.productScreen.data.ProductScreenEvent
import mpshop.composeapp.generated.resources.Res
import mpshop.composeapp.generated.resources.icon_close_24dp
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetProductView(
    showBottomSheet: () -> Unit,
    text: String,
    content: @Composable () -> Unit
) {

    val modalBottomSheetSizeState = rememberStandardBottomSheetState(
        skipHiddenState = false
    )

    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        sheetState = modalBottomSheetSizeState,
        onDismissRequest = {
            scope.launch { modalBottomSheetSizeState.hide() }.invokeOnCompletion {
                if (!modalBottomSheetSizeState.isVisible) {
                   showBottomSheet()
                }
            }
        },
        dragHandle = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = text,
                    textAlign = TextAlign.Start,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
                IconButton(
                    onClick = {
                        scope.launch { modalBottomSheetSizeState.hide() }.invokeOnCompletion {
                            if (!modalBottomSheetSizeState.isVisible) {
                                showBottomSheet()
                            }
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.icon_close_24dp),
                        contentDescription = "Close select $text"
                    )
                }
            }
        }
    ) {
        content()
    }
}