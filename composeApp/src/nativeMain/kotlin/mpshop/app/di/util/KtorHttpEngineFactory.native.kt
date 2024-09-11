package mpshop.app.di.util

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

actual class KtorHttpEngineFactory {
    actual fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig> = Darwin
}