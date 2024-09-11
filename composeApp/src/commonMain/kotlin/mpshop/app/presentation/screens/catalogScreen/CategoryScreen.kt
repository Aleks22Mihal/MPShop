package mpshop.app.presentation.screens.catalogScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import mpshop.app.presentation.appComponents.IconButtonView
import mpshop.app.presentation.screens.catalogScreen.data.CategoryScreenEvent
import mpshop.app.presentation.screens.catalogScreen.views.CardCategoryView
import mpshop.composeapp.generated.resources.Res
import mpshop.composeapp.generated.resources.arrow_left_icon_16dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    categoryScreenComponent: CategoryScreenComponent
) {

    val state by categoryScreenComponent.state.subscribeAsState()

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text("Categories")
                },
                navigationIcon = {
                    IconButtonView(
                        onClick = {
                            categoryScreenComponent.onEvent(CategoryScreenEvent.OnClickBack)
                        },
                        iconDrawable = Res.drawable.arrow_left_icon_16dp,
                        contentDescription = "Back",
                    )
                },
            )
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
        ) {
            items(state.listCategories) { category ->
                CardCategoryView(
                    category = category,
                    onEvent = categoryScreenComponent::onEvent,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}
