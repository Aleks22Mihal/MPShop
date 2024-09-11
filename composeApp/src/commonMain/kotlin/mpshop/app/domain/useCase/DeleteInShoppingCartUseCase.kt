package mpshop.app.domain.useCase

import mpshop.app.domain.repository.AppRepository

class DeleteInShoppingCartUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(productId: Int) = repository.deleteInShoppingCart(
        productId = productId
    )
}
