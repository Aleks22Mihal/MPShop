package mpshop.app.presentation.screens.productScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import mpshop.app.presentation.screens.productScreen.data.ModalBottomSheetType
import mpshop.app.presentation.screens.productScreen.data.ProductScreenEvent
import mpshop.app.presentation.screens.productScreen.views.BottomAddShoppingCartView
import mpshop.app.presentation.screens.productScreen.views.CardImageProductView
import mpshop.app.presentation.screens.productScreen.views.ColorCardProductView
import mpshop.app.presentation.screens.productScreen.views.ColorContentBottomSheetView
import mpshop.app.presentation.screens.productScreen.views.ModalBottomSheetProductView
import mpshop.app.presentation.screens.productScreen.views.QuantityCardProductView
import mpshop.app.presentation.screens.productScreen.views.SizeCardProductView
import mpshop.app.presentation.screens.productScreen.views.SizeContentBottomSheetView

@Composable
fun ProductScreen(
    productScreenComponent: ProductScreenComponent
) {
    val state by productScreenComponent.state.subscribeAsState()

    Scaffold(
        bottomBar = {
            BottomAddShoppingCartView(
                state = state,
                onEvent = productScreenComponent::onEvent,
            )
        },
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            CardImageProductView(
                listImage = state.product?.images ?: emptyList(),
                onEvent = productScreenComponent::onEvent
            )

            Text(
                text = state.product?.title ?: "",
                textAlign = TextAlign.Start,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )

            SizeCardProductView(
                state = state,
                onEvent = productScreenComponent::onEvent,
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )

            ColorCardProductView(
                state = state,
                onEvent = productScreenComponent::onEvent,
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )

            QuantityCardProductView(
                state = state,
                onEvent = productScreenComponent::onEvent,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )

            Text(
                text = "Description",
                textAlign = TextAlign.Start,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Text(
                text = state.product?.description ?: "",
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }

        if (state.showTypeBottomSheet != null) {

            ModalBottomSheetProductView(
                showBottomSheet = {
                    productScreenComponent.onEvent(
                        ProductScreenEvent.ShowBottomSheet(null))
                },
                text = when (state.showTypeBottomSheet) {
                    ModalBottomSheetType.Size -> "Size"
                    ModalBottomSheetType.Color -> "Color"
                    else -> ""
                },
            ) {
                when (state.showTypeBottomSheet) {
                    ModalBottomSheetType.Size -> {
                        SizeContentBottomSheetView(
                            state = state,
                            onEvent = productScreenComponent::onEvent
                        )
                    }

                    ModalBottomSheetType.Color -> ColorContentBottomSheetView(
                        state = state,
                        onEvent = productScreenComponent::onEvent
                    )

                    else -> {}
                }
            }
        }
    }
}
