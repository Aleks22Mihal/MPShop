package mpshop.app.data.dataSource.location

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import mpshop.app.common.NetworkError
import mpshop.app.common.Result
import mpshop.app.data.models.ProductDTO

class LocationClient : LocationApi {

    override val shoppingCartProducts: MutableList<ProductDTO> = mutableListOf()
    override val favoriteProducts: MutableList<ProductDTO> = mutableListOf()

    private val _favoriteProductsState: MutableValue<MutableList<ProductDTO>> = MutableValue(mutableListOf())
    private val favoriteProductsState: Value<List<ProductDTO>> = _favoriteProductsState

    override suspend fun addInShoppingCart(product: ProductDTO): Result<Unit, NetworkError> {
        shoppingCartProducts.add(product)
        delay(1000)
        return Result.Success(Unit)
    }

    override suspend fun addInFavorite(product: ProductDTO): Result<Unit, NetworkError> {
        favoriteProducts.add(product)
        _favoriteProductsState.value.add(product)
        println("add product: ${_favoriteProductsState.value}")
        delay(1000)
        return Result.Success(Unit)
    }

    override suspend fun deleteInShoppingCart(productId: Int): Result<Unit, NetworkError> {
        shoppingCartProducts.removeAll {
            it.id == productId
        }
        delay(1000)
        return Result.Success(Unit)
    }

    override suspend fun deleteInFavorite(productId: Int): Result<Unit, NetworkError> {
        favoriteProducts.removeAll {
            it.id == productId
        }
        _favoriteProductsState.value = shoppingCartProducts
        delay(1000)
        return Result.Success(Unit)
    }

    override suspend fun getProductsInShoppingCart(): Flow<Result<List<ProductDTO>, NetworkError>> {
        return flow { emit(Result.Success(shoppingCartProducts)) }
    }

    override suspend fun getProductsInFavorite(): Value<List<ProductDTO>> {
       // println("return data : ${favoriteProducts}")
        println("return data : ${favoriteProductsState.value}")
        return favoriteProductsState
    }

}