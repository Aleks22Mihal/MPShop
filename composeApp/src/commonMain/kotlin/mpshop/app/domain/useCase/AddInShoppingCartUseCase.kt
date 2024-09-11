package mpshop.app.domain.useCase

import mpshop.app.domain.repository.AppRepository
import mpshop.app.presentation.mappers.toMapProductDBO
import mpshop.app.presentation.models.Product

class AddInShoppingCartUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(product: Product) = repository.addInShoppingCart(
        product = product.toMapProductDBO()
    )
}