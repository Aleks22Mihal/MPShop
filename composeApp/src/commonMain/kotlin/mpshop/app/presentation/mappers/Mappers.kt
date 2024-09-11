package mpshop.app.presentation.mappers

import mpshop.app.domain.models.CategoryDBO
import mpshop.app.domain.models.ProductDBO
import mpshop.app.presentation.models.Category
import mpshop.app.presentation.models.Product

fun Category.toMapCategoryDBO() = CategoryDBO(
    creationAt = creationAt,
    id = id,
    image = image,
    name = name,
    updatedAt = updatedAt,
)

fun Product.toMapProductDBO() = ProductDBO(
    id = id,
    title = title,
    price = price,
    category = category.toMapCategoryDBO(),
    description = description,
    creationAt = creationAt,
    images = images,
    updatedAt = updatedAt
)
