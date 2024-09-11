package mpshop.app.presentation.appComponents

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import io.kamel.core.utils.cacheControl
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.client.utils.CacheControl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import mpshop.app.presentation.utils.ensureValidUrl

@Composable
fun ImageAsyncLoad(
    data: Any,
    contentDescription: String?,
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier = Modifier
) {

    KamelImage(
        resource = asyncPainterResource(
            data = if (data is String) data.ensureValidUrl() else data,
            filterQuality = FilterQuality.High,
            block = {
                coroutineContext = Job() + Dispatchers.IO
                requestBuilder {
                    cacheControl(CacheControl.NO_CACHE)
                }.build()
            }
        ),
        contentDescription = contentDescription,
        contentScale = contentScale,
        animationSpec = tween(),
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surfaceContainerHigh),
        onLoading = { progress ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.surfaceContainerHigh),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = {
                        progress
                    }
                )
            }
        },
        onFailure = { error ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.surfaceContainerHigh),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Error",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}
