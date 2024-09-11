package mpshop.app.data.dataSource.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import mpshop.app.common.NetworkError
import mpshop.app.common.Result
import mpshop.app.data.models.CategoryDTO
import mpshop.app.data.models.ProductDTO

class AppClient(
    private val httpClient: HttpClient
) : ApiService {

    override suspend fun getAllCategories(): Result<List<CategoryDTO>, NetworkError> {
        return getResult<List<CategoryDTO>>(
            httpResponse = httpClient.get {
                url("api/v1/categories")
            }
        )
    }

    override suspend fun getLimitProducts(limit: Int): Result<List<ProductDTO>, NetworkError> {
        return getResult<List<ProductDTO>>(
            httpResponse = httpClient.get {
                url("/api/v1/products")
                parameter("limit", "$limit")
                parameter("offset", "0")
            }
        )
    }

    override suspend fun getAllProducts(): Result<List<ProductDTO>, NetworkError> {
        return getResult<List<ProductDTO>>(
            httpResponse = httpClient.get {
                url("/api/v1/products")
            }
        )
    }

    override suspend fun getProduct(productId: String): Result<ProductDTO, NetworkError> {
        return getResult<ProductDTO>(
            httpResponse = httpClient.get {
                url("api/v1/products/$productId")
            }
        )
    }

    override suspend fun getProductFromCategory(categoryId: Int): Result<List<ProductDTO>, NetworkError> {
        return getResult<List<ProductDTO>>(
            httpResponse = httpClient.get {
                url("api/v1/products")
                parameter("categoryId", "$categoryId")
            }
        )
    }

    override suspend fun searchProducts(
        categoryId: Int?,
        title: String?,
        priceMin: Int?,
        priceMax: Int?,
    ): Result<List<ProductDTO>, NetworkError> {
        return getResult<List<ProductDTO>>(
            httpResponse = httpClient.get {
                url("api/v1/products")
                if (categoryId != null) parameter("categoryId", "$categoryId")
                if (title != null) parameter("title", title)
                if (priceMin != null) parameter("price_min", "$priceMin")
                if (priceMax != null) parameter("price_max", "$priceMax")
            }
        )
    }

    private suspend inline fun <reified T> getResult(
        httpResponse: HttpResponse,
    ): Result<T, NetworkError> {

        val response = try {
            httpResponse
        } catch (e: UnresolvedAddressException) {
            e.printStackTrace()
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            e.printStackTrace()
            return Result.Error(NetworkError.SERIALIZATION)
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.Error(NetworkError.UNKNOWN)
        }

        return when (response.status.value) {
            in 200..299 -> {
                val data = response.body<T>()
                Result.Success(data)
            }

            401 -> Result.Error(NetworkError.UNAUTHORIZED)

            409 -> Result.Error(NetworkError.CONFLICT)

            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)

            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)

            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)

            else -> {
                println(response.status.value)
                Result.Error(NetworkError.UNKNOWN)
            }
        }
    }
}
