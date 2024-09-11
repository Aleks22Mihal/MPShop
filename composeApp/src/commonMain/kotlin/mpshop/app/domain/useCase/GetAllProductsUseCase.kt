package mpshop.app.domain.useCase

import mpshop.app.common.resultMap
import mpshop.app.domain.mappers.toMapProduct
import mpshop.app.domain.repository.AppRepository

class GetAllProductsUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke() = repository.getAllProducts().resultMap { list ->
        list.map { prod ->
            prod.toMapProduct()
        }
    }
}