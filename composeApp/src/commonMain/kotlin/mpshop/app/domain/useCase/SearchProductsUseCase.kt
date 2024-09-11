package mpshop.app.domain.useCase

import mpshop.app.common.resultMap
import mpshop.app.domain.mappers.toMapProduct
import mpshop.app.domain.repository.AppRepository

class SearchProductsUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(
        categoryId: Int?,
        title: String?,
        priceMin: Int?,
        priceMax: Int?
    ) = repository.searchProducts(
        categoryId = categoryId,
        title = title,
        priceMin = priceMin,
        priceMax = priceMax
    ).resultMap { listProducts ->
        listProducts.map { product ->
            product.toMapProduct()
        }
    }
}