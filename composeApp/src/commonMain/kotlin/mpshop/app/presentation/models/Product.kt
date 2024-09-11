package mpshop.app.presentation.models

import androidx.compose.ui.graphics.Color

data class Product(
    val category: Category,
    val creationAt: String,
    val description: String,
    val id: Int,
    val images: List<String>,
    val size: List<String> = listOf(
        "XS",
        "S",
        "M",
        "L",
        "XL",
        "XXL",
        "XS",
        "S",
        "M",
        "L",
        "XL",
        "XXL"
    ),
    val colors: List<Color> = listOf(
        Color.White,
        Color.Black,
        Color.Red,
        Color.Cyan,
        Color.Blue,
        Color.Green,
        Color.Yellow,
        Color.Magenta,
    ),
    val price: Long,
    val title: String,
    val updatedAt: String
)