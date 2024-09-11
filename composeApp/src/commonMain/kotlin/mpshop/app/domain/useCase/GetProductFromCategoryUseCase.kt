package mpshop.app.domain.useCase

import mpshop.app.common.resultMap
import mpshop.app.domain.mappers.toMapProduct
import mpshop.app.domain.repository.AppRepository

class GetProductFromCategoryUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(categoryId: Int) =
        repository.getProductFromCategory(categoryId).resultMap { list ->
            list.map { prod ->
                prod.toMapProduct()
            }
        }
}