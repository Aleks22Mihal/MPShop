package mpshop.app.data.dataSource.remote

import mpshop.app.common.NetworkError
import mpshop.app.common.Result
import mpshop.app.data.models.CategoryDTO
import mpshop.app.data.models.ProductDTO

interface ApiService {
    suspend fun getAllCategories(): Result<List<CategoryDTO>, NetworkError>
    suspend fun getLimitProducts(limit: Int): Result<List<ProductDTO>, NetworkError>
    suspend fun getAllProducts(): Result<List<ProductDTO>, NetworkError>
    suspend fun getProduct(productId: String): Result<ProductDTO, NetworkError>
    suspend fun getProductFromCategory(categoryId: Int): Result<List<ProductDTO>, NetworkError>
    suspend fun searchProducts(
        categoryId: Int?,
        title: String?,
        priceMin: Int?,
        priceMax: Int?
    ): Result<List<ProductDTO>, NetworkError>
}