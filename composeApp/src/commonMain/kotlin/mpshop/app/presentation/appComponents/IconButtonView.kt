package mpshop.app.presentation.appComponents

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun IconButtonView(
    onClick: () -> Unit,
    iconDrawable: DrawableResource,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = {
            onClick()
        },
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
        ),
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(iconDrawable),
            contentDescription = contentDescription
        )
    }
}