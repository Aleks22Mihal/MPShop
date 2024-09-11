package mpshop.app.domain.useCase

import com.arkivanov.decompose.value.operator.map
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import mpshop.app.common.resultMap
import mpshop.app.domain.mappers.toMapProduct
import mpshop.app.domain.repository.AppRepository

class GetAllProductsInFavoriteUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke() = repository.getProductsInFavorite().map { listProducts ->
        listProducts.map { product ->
            product.toMapProduct()
        }
    }
}
