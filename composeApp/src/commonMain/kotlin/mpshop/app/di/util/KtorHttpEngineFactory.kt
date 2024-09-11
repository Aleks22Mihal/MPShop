package mpshop.app.di.util

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

expect class KtorHttpEngineFactory() {
    fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig>
}