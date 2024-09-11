package mpshop.app.presentation.screens.homeScreen.views

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import mpshop.app.presentation.screens.homeScreen.data.HomeScreenEvent
import mpshop.app.presentation.screens.homeScreen.data.HomeScreenState
import mpshop.app.presentation.screens.productScreen.data.ModalBottomSheetType
import mpshop.app.presentation.screens.productScreen.views.ModalBottomSheetProductView
import mpshop.app.presentation.utils.clearFocusOnKeyboardDismiss
import mpshop.composeapp.generated.resources.Res
import mpshop.composeapp.generated.resources.arrow_left_icon_16dp
import mpshop.composeapp.generated.resources.icon_close_24dp
import mpshop.composeapp.generated.resources.search_icon_16dp
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarView(
    state: HomeScreenState,
    onEvent: (HomeScreenEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    val focusManager = FocusRequester()

    SearchBar(
        query = state.searchBarText,
        onQueryChange = { queryText ->
            onEvent(
                HomeScreenEvent.ChangeSearchBarText(
                    queryText = queryText
                )
            )
        },
        onSearch = { queryText ->
            onEvent(
                HomeScreenEvent.ChangeSearchBarText(
                    queryText = queryText
                )
            )
        },
        leadingIcon = {
            AnimatedContent(
                targetState = state.isActiveSearchBar
            ) { isActiveSearchBar ->
                if (isActiveSearchBar) {
                    IconButton(
                        onClick = {
                            onEvent(HomeScreenEvent.ActiveChangeSearchBar)
                        }
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.arrow_left_icon_16dp),
                            contentDescription = "Search"
                        )
                    }
                } else {
                    Icon(
                        painter = painterResource(Res.drawable.search_icon_16dp),
                        contentDescription = "Search"
                    )
                }
            }
        },
        trailingIcon = {
            AnimatedVisibility(
                visible = state.searchBarText.isNotBlank(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                IconButton(
                    onClick = {
                        onEvent(HomeScreenEvent.ChangeSearchBarText(""))
                    }
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.icon_close_24dp),
                        contentDescription = "Search"
                    )
                }
            }
        },
        placeholder = {
            Text(text = "Search")
        },
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
        ),
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
        windowInsets = WindowInsets(
            top = animateDpAsState(
                targetValue = if (state.isActiveSearchBar) 64.dp else 0.dp
            ).value
        ),
        active = state.isActiveSearchBar,
        onActiveChange = {
            if (state.searchBarText.isBlank() && !state.isActiveSearchBar) {
                onEvent(HomeScreenEvent.ActiveChangeSearchBar)
            }
        },
        modifier = modifier
        //   .clearFocusOnKeyboardDismiss()
    ) {
        Column {
            Button(
                onClick = {
                    onEvent(HomeScreenEvent.ShowBottomSheet(ModalBottomSheetType.Category))
                }
            ) {}
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp),
            ) {
                items(state.searchListProducts) { product ->
                    CardSearchProductView(
                        product = product,
                        onEvent = onEvent,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
        if (state.showBottomSheet != null) {
            ModalBottomSheetProductView(
                showBottomSheet = {
                    onEvent(HomeScreenEvent.ShowBottomSheet(null))
                },
                text = "asdasd"
            ) {
                LazyColumn {
                    items(state.categories) {
                        Text(it.name)
                    }
                }
            }
        }
    }
}