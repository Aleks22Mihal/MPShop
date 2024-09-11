package mpshop.app.presentation.screens.homeScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import mpshop.app.presentation.appComponents.TopAppBarSurface
import mpshop.app.presentation.screens.homeScreen.views.BannerView
import mpshop.app.presentation.screens.homeScreen.views.CategorySelectionView
import mpshop.app.presentation.screens.homeScreen.views.ProductSelectionView
import mpshop.app.presentation.screens.homeScreen.views.SearchBarView
import mpshop.app.presentation.screens.homeScreen.views.SearchTextFieldView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeScreenComponent: HomeScreenComponent
) {

    val state by homeScreenComponent.state.subscribeAsState()

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

//    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AnimatedVisibility(
                    visible = !state.isActiveSearchBar,
                    enter = expandVertically(),
                    exit = shrinkVertically()
                ) {
                    TopAppBar(
                        title = {
                            Text("Home")
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            scrolledContainerColor = MaterialTheme.colorScheme.background
                        ),
                        scrollBehavior = scrollBehavior
                    )
                }
                Surface {
                    SearchBarView(
                        state = state,
                        onEvent = homeScreenComponent::onEvent,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = animateDpAsState(
                                    if (state.isActiveSearchBar) 0.dp else 16.dp
                                ).value
                            )
                            .padding(bottom = 16.dp)
                    )
                }
            }
        },
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)/*
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        focusManager.clearFocus()
                    }
                )
            }*/
    ) { innerPadding ->


        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = innerPadding.calculateTopPadding())
        ) {

            BannerView()

            CategorySelectionView(
                state = state,
                onEvent = homeScreenComponent::onEvent
            )

            ProductSelectionView(
                productSelectionName = "Top",
                productSelectionItem = state.topProducts,
                onEvent = homeScreenComponent::onEvent
            )

            ProductSelectionView(
                productSelectionName = "Sale",
                productSelectionItem = state.saleProducts,
                onEvent = homeScreenComponent::onEvent
            )

            ProductSelectionView(
                productSelectionName = "New Collection",
                productSelectionItem = state.newCollectionProducts,
                onEvent = homeScreenComponent::onEvent
            )
        }
    }
}
