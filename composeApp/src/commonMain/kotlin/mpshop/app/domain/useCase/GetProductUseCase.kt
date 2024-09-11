package mpshop.app.domain.useCase

import mpshop.app.common.resultMap
import mpshop.app.domain.mappers.toMapProduct
import mpshop.app.domain.repository.AppRepository

class GetProductUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(productId: String) = repository.getProduct(productId).resultMap {
        it.toMapProduct()
    }
}