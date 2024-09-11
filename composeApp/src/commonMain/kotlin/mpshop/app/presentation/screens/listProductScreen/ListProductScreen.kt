package mpshop.app.presentation.screens.listProductScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import mpshop.app.presentation.appComponents.CardProductView
import mpshop.app.presentation.appComponents.IconButtonView
import mpshop.app.presentation.screens.listProductScreen.data.ListProductScreenEvent
import mpshop.composeapp.generated.resources.Res
import mpshop.composeapp.generated.resources.arrow_left_icon_16dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListProductScreen(listProductScreenComponent: ListProductScreenComponent) {

    val state by listProductScreenComponent.state.subscribeAsState()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(state.nameCategory)
                },
                navigationIcon = {
                    IconButtonView(
                        onClick = {
                            listProductScreenComponent.onEvent(ListProductScreenEvent.OnClickBack)
                        },
                        iconDrawable = Res.drawable.arrow_left_icon_16dp,
                        contentDescription = "Back",
                    )
                },
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 8.dp),
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding())
        ) {
            items(state.listProducts) { product ->
                CardProductView(
                    product = product,
                    onClick = {
                        listProductScreenComponent.onEvent(
                            ListProductScreenEvent.OnClickProduct(
                                productId = product.id.toString()
                            )
                        )
                    },
                    onClickFavorite = {},
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

    }
}