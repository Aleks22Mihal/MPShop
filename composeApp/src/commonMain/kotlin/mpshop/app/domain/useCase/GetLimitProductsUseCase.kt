package mpshop.app.domain.useCase

import mpshop.app.common.resultMap
import mpshop.app.domain.mappers.toMapProduct
import mpshop.app.domain.repository.AppRepository

class GetLimitProductsUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(limit: Int) = repository.getLimitProducts(
        limit = limit
    ).resultMap { list ->
        list.map { prod ->
            prod.toMapProduct()
        }
    }
}