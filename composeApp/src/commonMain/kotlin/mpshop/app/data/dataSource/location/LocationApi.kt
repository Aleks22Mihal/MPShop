package mpshop.app.data.dataSource.location

import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import mpshop.app.common.NetworkError
import mpshop.app.common.Result
import mpshop.app.data.models.ProductDTO

interface LocationApi {

    val shoppingCartProducts: MutableList<ProductDTO>
    val favoriteProducts: MutableList<ProductDTO>

    suspend fun addInShoppingCart(product: ProductDTO): Result<Unit, NetworkError>
    suspend fun addInFavorite(product: ProductDTO): Result<Unit, NetworkError>

    suspend fun deleteInShoppingCart(productId: Int): Result<Unit, NetworkError>
    suspend fun deleteInFavorite(productId: Int): Result<Unit, NetworkError>

    suspend fun getProductsInShoppingCart(): Flow<Result<List<ProductDTO>, NetworkError>>
     suspend fun getProductsInFavorite(): Value<List<ProductDTO>>
}