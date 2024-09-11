package mpshop.app.presentation.screens.homeScreen.views

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import mpshop.composeapp.generated.resources.Res
import mpshop.composeapp.generated.resources.banner1
import mpshop.composeapp.generated.resources.banner2
import mpshop.composeapp.generated.resources.banner3
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerView(
    bannersList: List<DrawableResource> = listOf(
        Res.drawable.banner1,
        Res.drawable.banner2,
        Res.drawable.banner3,
    ),
    timeScroll: Long = 3000,
    modifier: Modifier = Modifier
) {

    val bigNumberForInfinityScroll = 400

    val (pageCount, middlePage) = with(bannersList) {
        if (size > 1) {
            Pair(size * bigNumberForInfinityScroll, size * bigNumberForInfinityScroll / 2)
        } else Pair(size, size)
    }

    val pagerState = rememberPagerState(
        pageCount = { pageCount },
        initialPage = middlePage
    )


    val isDragged by pagerState.interactionSource.interactions.collectAsState(null)
    var key by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(
                horizontal = 16.dp
            ),
            pageSpacing = 16.dp,
            pageSize = PageSize.Fixed(260.dp),
            verticalAlignment = Alignment.Top,
            beyondBoundsPageCount = 1
        ) { page ->
            Card(
                onClick = {},
                modifier = Modifier
                    .height(160.dp)
                    .width(260.dp)
            ) {
              Image(
                    painter = painterResource(Res.drawable.banner1),
                    contentDescription = bannersList[page % bannersList.size].toString(),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

    LaunchedEffect(isDragged) {
        if (isDragged is DragInteraction.Stop) key = !key
    }

    LaunchedEffect(key) {
        delay(timeScroll)
        pagerState.animateScrollToPage(
            page = pagerState.currentPage.inc() % pagerState.pageCount,
            animationSpec = tween(
                durationMillis = 500, easing = FastOutLinearInEasing
            )
        )
        key = !key
    }
}
