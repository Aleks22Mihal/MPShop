package mpshop.app.data.repository

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import mpshop.app.common.NetworkError
import mpshop.app.common.Result
import mpshop.app.common.resultMap
import mpshop.app.data.dataSource.location.LocationClient
import mpshop.app.data.dataSource.remote.AppClient
import mpshop.app.data.mappers.toMapDBO
import mpshop.app.domain.mappers.toMapProductDTO
import mpshop.app.domain.models.CategoryDBO
import mpshop.app.domain.models.ProductDBO
import mpshop.app.domain.repository.AppRepository

class AppRepositoryImpl(
    private val appClient: AppClient,
    private val locationClient: LocationClient,
) : AppRepository {
    override suspend fun getAllCategories(): Result<List<CategoryDBO>, NetworkError> {
        return appClient.getAllCategories().resultMap { list ->
            list.map { category ->
                category.toMapDBO()
            }
        }
    }

    override suspend fun getLimitProducts(limit: Int): Result<List<ProductDBO>, NetworkError> {
        return appClient.getLimitProducts(limit = limit).resultMap { list ->
            list.map { prod ->
                prod.toMapDBO()
            }
        }
    }

    override suspend fun getAllProducts(): Result<List<ProductDBO>, NetworkError> {
        return appClient.getAllProducts().resultMap { list ->
            list.map { prod ->
                prod.toMapDBO()
            }
        }
    }

    override suspend fun getProduct(productId: String): Result<ProductDBO, NetworkError> {
        return appClient.getProduct(productId).resultMap { product ->
            product.toMapDBO()
        }
    }

    override suspend fun getProductFromCategory(categoryId: Int): Result<List<ProductDBO>, NetworkError> {
        return appClient.getProductFromCategory(categoryId).resultMap { list ->
            list.map { prod ->
                prod.toMapDBO()
            }
        }
    }

    override suspend fun searchProducts(
        categoryId: Int?,
        title: String?,
        priceMin: Int?,
        priceMax: Int?
    ): Result<List<ProductDBO>, NetworkError> {
        return appClient.searchProducts(
            categoryId, title, priceMin, priceMax
        ).resultMap { list ->
            list.map { prod ->
                prod.toMapDBO()
            }
        }
    }


    //Location
    override suspend fun addInShoppingCart(product: ProductDBO): Result<Unit, NetworkError> {
        return locationClient.addInShoppingCart(
            product = product.toMapProductDTO()
        )
    }

    override suspend fun addInFavorite(product: ProductDBO): Result<Unit, NetworkError> {
        return locationClient.addInFavorite(
            product = product.toMapProductDTO()
        )
    }

    override suspend fun deleteInShoppingCart(productId: Int): Result<Unit, NetworkError> {
        return locationClient.deleteInShoppingCart(productId)
    }

    override suspend fun deleteInFavorite(productId: Int): Result<Unit, NetworkError> {
        return locationClient.deleteInFavorite(productId)
    }


    override suspend fun getProductsInShoppingCart(): Flow<Result<List<ProductDBO>, NetworkError>> {
        return locationClient.getProductsInShoppingCart().map { result ->
            result.resultMap { list ->
                list.map { productDTO ->
                    productDTO.toMapDBO()
                }
            }
        }
    }

    override suspend fun getProductsInFavorite(): Value<List<ProductDBO>> {
        return locationClient.getProductsInFavorite().map { listProducts ->
            listProducts.map { product ->
                product.toMapDBO()
            }
        }
    }
}
