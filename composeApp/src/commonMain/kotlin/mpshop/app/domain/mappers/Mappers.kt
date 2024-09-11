package mpshop.app.domain.mappers

import mpshop.app.data.models.CategoryDTO
import mpshop.app.data.models.ProductDTO
import mpshop.app.domain.models.CategoryDBO
import mpshop.app.domain.models.ProductDBO
import mpshop.app.presentation.models.Category
import mpshop.app.presentation.models.Product

fun CategoryDBO.toMapCategory() = Category(
    creationAt = creationAt,
    id = id,
    image = image,
    name = name,
    updatedAt = updatedAt,
)

fun ProductDBO.toMapProduct() = Product(
    id = id,
    title = title,
    price = price,
    category = category.toMapCategory(),
    description = description,
    creationAt = creationAt,
    images = images,
    updatedAt = updatedAt
)


fun CategoryDBO.toMapCategoryDTO() = CategoryDTO(
    creationAt = creationAt,
    id = id,
    image = image,
    name = name,
    updatedAt = updatedAt,
)

fun ProductDBO.toMapProductDTO() = ProductDTO(
    id = id,
    title = title,
    price = price,
    category = category.toMapCategoryDTO(),
    description = description,
    creationAt = creationAt,
    images = images,
    updatedAt = updatedAt
)