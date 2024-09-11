package mpshop.app.presentation.screens.productScreen.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import mpshop.app.presentation.appComponents.IconButtonView
import mpshop.app.presentation.appComponents.ImageAsyncLoad
import mpshop.app.presentation.screens.productScreen.data.ProductScreenEvent
import mpshop.composeapp.generated.resources.Res
import mpshop.composeapp.generated.resources.arrow_left_icon_16dp
import mpshop.composeapp.generated.resources.heart_icon_16dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardImageProductView(
    listImage: List<String>,
    onEvent: (ProductScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    val pagerState = rememberPagerState(
        pageCount = { listImage.size },
    )

    Box(
        modifier = modifier
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .heightIn(min = 300.dp)
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
        ) { page ->
            ImageAsyncLoad(
                data = listImage[page],
                contentDescription = "product image",
                modifier = Modifier.fillMaxSize()
            )
        }
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) {
                    MaterialTheme.colorScheme.background
                } else Color.LightGray

                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 46.dp)
                .align(Alignment.TopCenter)
        ) {
            IconButtonView(
                onClick = {
                    onEvent(ProductScreenEvent.OnClickBack)
                },
                iconDrawable = Res.drawable.arrow_left_icon_16dp,
                contentDescription = "Back",
            )
            IconButtonView(
                onClick = {

                },
                iconDrawable = Res.drawable.heart_icon_16dp,
                contentDescription = "Like",
            )
        }

    }
}