package mpshop.app.presentation.appComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import mpshop.app.presentation.navigation.rootNavigation.Configuration
import mpshop.app.presentation.navigation.rootNavigation.RootComponent
import mpshop.composeapp.generated.resources.Res
import mpshop.composeapp.generated.resources.home_icon_24dp
import mpshop.composeapp.generated.resources.icon_heart_24dp
import mpshop.composeapp.generated.resources.profile_icon_24dp
import mpshop.composeapp.generated.resources.shopping_cart_icon_24dp
import org.jetbrains.compose.resources.vectorResource

@Composable
fun BottomBarNavigation(
    onItemSelected: (Configuration) -> Unit,
    currentInstance: RootComponent.Child
) {
    val listScreen = listOf(
        Configuration.Home,
        Configuration.ShoppingCart,
        Configuration.Favorite,
        Configuration.Profile
    )
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
    ) {
        listScreen.forEach { screen ->

            val isSelected = when (currentInstance) {
                is RootComponent.Child.Home -> screen is Configuration.Home
                is RootComponent.Child.ShoppingCart -> screen is Configuration.ShoppingCart
                is RootComponent.Child.Favorite -> screen is Configuration.Favorite
                is RootComponent.Child.Profile -> screen is Configuration.Profile
            }

            IconButton(
                onClick = {
                    onItemSelected(screen)
                }
            ) {
                Icon(
                    imageVector = vectorResource(
                        when (screen) {
                            Configuration.Home -> Res.drawable.home_icon_24dp
                            Configuration.ShoppingCart -> Res.drawable.shopping_cart_icon_24dp
                            Configuration.Favorite -> Res.drawable.icon_heart_24dp
                            Configuration.Profile -> Res.drawable.profile_icon_24dp
                        }
                    ),
                    tint = if (isSelected) {
                        MaterialTheme.colorScheme.primary
                    } else Color.Gray,
                    contentDescription = null,
                )
            }
        }
    }
}