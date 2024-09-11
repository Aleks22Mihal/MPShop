package mpshop.app.domain.useCase

import mpshop.app.domain.repository.AppRepository

class DeleteInFavoriteUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(productId: Int) = repository.deleteInFavorite(
        productId = productId
    )
}
