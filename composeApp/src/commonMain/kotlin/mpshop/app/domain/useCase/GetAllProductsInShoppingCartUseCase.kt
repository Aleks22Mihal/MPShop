package mpshop.app.domain.useCase

import kotlinx.coroutines.flow.map
import mpshop.app.common.resultMap
import mpshop.app.domain.mappers.toMapProduct
import mpshop.app.domain.repository.AppRepository

class GetAllProductsInShoppingCartUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke() = repository.getProductsInShoppingCart().map { result ->
        result.resultMap { list ->
            list.map { product ->
                product.toMapProduct()
            }
        }
    }
}
