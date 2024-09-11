package mpshop.app.di

import mpshop.app.domain.useCase.AddInFavoriteUseCase
import mpshop.app.domain.useCase.AddInShoppingCartUseCase
import mpshop.app.domain.useCase.DeleteInFavoriteUseCase
import mpshop.app.domain.useCase.DeleteInShoppingCartUseCase
import mpshop.app.domain.useCase.GetAllCategoriesUseCase
import mpshop.app.domain.useCase.GetAllProductsInFavoriteUseCase
import mpshop.app.domain.useCase.GetAllProductsInShoppingCartUseCase
import mpshop.app.domain.useCase.GetAllProductsUseCase
import mpshop.app.domain.useCase.GetLimitProductsUseCase
import mpshop.app.domain.useCase.GetProductFromCategoryUseCase
import mpshop.app.domain.useCase.GetProductUseCase
import mpshop.app.domain.useCase.SearchProductsUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::GetAllProductsUseCase)
    singleOf(::GetLimitProductsUseCase)
    singleOf(::GetAllCategoriesUseCase)
    singleOf(::GetProductUseCase)
    singleOf(::GetProductFromCategoryUseCase)
    singleOf(::AddInFavoriteUseCase)
    singleOf(::AddInShoppingCartUseCase)
    singleOf(::DeleteInFavoriteUseCase)
    singleOf(::DeleteInShoppingCartUseCase)
    singleOf(::GetAllProductsInFavoriteUseCase)
    singleOf(::GetAllProductsInShoppingCartUseCase)
    singleOf(::SearchProductsUseCase)
}