package mpshop.app.presentation.screens.homeScreen.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import mpshop.app.presentation.utils.clearFocusOnKeyboardDismiss
import mpshop.composeapp.generated.resources.Res
import mpshop.composeapp.generated.resources.search_icon_16dp
import org.jetbrains.compose.resources.vectorResource

@Composable
fun SearchTextFieldView(
    modifier: Modifier = Modifier,
) {

    var text by remember { mutableStateOf("") }
    BasicTextField(
        value = text,
        onValueChange = {
            text = it
        },
        maxLines = 1,
        modifier = modifier
            .clearFocusOnKeyboardDismiss()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
    ) { innerTextField ->
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.surfaceContainerHigh)
        ) {
            Box(modifier = Modifier.padding(horizontal = 8.dp)) {
                Row {
                    Icon(
                        imageVector = vectorResource(Res.drawable.search_icon_16dp),
                        contentDescription = "Search",
                        modifier = Modifier.padding(
                            horizontal = 8.dp
                        )
                    )
                    innerTextField()
                }
            }
        }
    }
}
