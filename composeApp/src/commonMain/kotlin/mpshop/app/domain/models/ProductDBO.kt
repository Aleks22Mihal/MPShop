package mpshop.app.domain.models

data class ProductDBO(
    val category: CategoryDBO,
    val creationAt: String,
    val description: String,
    val id: Int,
    val images: List<String>,
    val price: Long,
    val title: String,
    val updatedAt: String
)