package mpshop.app.data.mappers

import mpshop.app.data.models.CategoryDTO
import mpshop.app.data.models.ProductDTO
import mpshop.app.domain.models.CategoryDBO
import mpshop.app.domain.models.ProductDBO

fun CategoryDTO.toMapDBO() = CategoryDBO(
    creationAt = creationAt,
    id = id,
    image = image,
    name = name,
    updatedAt = updatedAt,
)

fun ProductDTO.toMapDBO() = ProductDBO(
    id = id,
    title = title,
    price = price,
    category = category.toMapDBO(),
    description = description,
    creationAt = creationAt,
    images = images,
    updatedAt = updatedAt
)