package mpshop.app.domain.repository

import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.Flow
import mpshop.app.common.NetworkError
import mpshop.app.common.Result
import mpshop.app.domain.models.CategoryDBO
import mpshop.app.domain.models.ProductDBO

interface AppRepository {
    suspend fun getAllCategories(): Result<List<CategoryDBO>, NetworkError>
    suspend fun getLimitProducts(limit: Int): Result<List<ProductDBO>, NetworkError>
    suspend fun getAllProducts(): Result<List<ProductDBO>, NetworkError>
    suspend fun getProduct(productId: String): Result<ProductDBO, NetworkError>
    suspend fun getProductFromCategory(categoryId: Int): Result<List<ProductDBO>, NetworkError>

    suspend fun addInShoppingCart(product: ProductDBO): Result<Unit, NetworkError>
    suspend fun addInFavorite(product: ProductDBO): Result<Unit, NetworkError>
    suspend fun deleteInShoppingCart(productId: Int): Result<Unit, NetworkError>
    suspend fun deleteInFavorite(productId: Int): Result<Unit, NetworkError>
    suspend fun searchProducts(
        categoryId: Int?,
        title: String?,
        priceMin: Int?,
        priceMax: Int?
    ): Result<List<ProductDBO>, NetworkError>


    suspend fun getProductsInShoppingCart(): Flow<Result<List<ProductDBO>, NetworkError>>


    suspend fun getProductsInFavorite(): Value<List<ProductDBO>>
}