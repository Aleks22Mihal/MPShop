package mpshop.app.data.models

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDTO(
    val creationAt: String,
    val id: Int,
    val image: String,
    val name: String,
    val updatedAt: String
)