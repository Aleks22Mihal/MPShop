package mpshop.app.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ProductDTO(
    val category: CategoryDTO,
    val creationAt: String,
    val description: String,
    val id: Int,
    val images: List<String>,
    val price: Long,
    val title: String,
    val updatedAt: String
)