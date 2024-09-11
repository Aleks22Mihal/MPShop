package mpshop.app.domain.useCase

import mpshop.app.common.resultMap
import mpshop.app.domain.mappers.toMapCategory
import mpshop.app.domain.repository.AppRepository

class GetAllCategoriesUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke() = repository.getAllCategories().resultMap { list ->
        list.map { prod ->
            prod.toMapCategory()
        }
    }
}