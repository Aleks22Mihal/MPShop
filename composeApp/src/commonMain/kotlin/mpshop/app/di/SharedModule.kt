package mpshop.app.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import mpshop.app.data.dataSource.location.LocationApi
import mpshop.app.data.dataSource.location.LocationClient
import mpshop.app.data.dataSource.remote.ApiService
import mpshop.app.data.dataSource.remote.AppClient
import mpshop.app.data.repository.AppRepositoryImpl
import mpshop.app.di.util.KtorHttpEngineFactory
import mpshop.app.domain.repository.AppRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedModule = module {

    single<HttpClient> {
        HttpClient(KtorHttpEngineFactory().createEngine()) {
            install(HttpTimeout) {
                socketTimeoutMillis = 60_000
                requestTimeoutMillis = 60_000
            }
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
            defaultRequest {
                host = "api.escuelajs.co"
                url {
                    protocol = URLProtocol.HTTPS
                }
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    singleOf(::LocationClient).bind<LocationApi>()
    singleOf(::AppClient).bind<ApiService>()
    singleOf(::AppRepositoryImpl).bind<AppRepository>()
}