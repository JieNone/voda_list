package ru.tyurin.vodovoz_test.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import ru.tyurin.vodovoz_test.domain.model.DataItem
import ru.tyurin.vodovoz_test.ui.stateholders.ProductsState
import ru.tyurin.vodovoz_test.ui.stateholders.ProductsViewModel


@Composable
fun CustomProductsScreen(viewModel: ProductsViewModel = hiltViewModel()) {
    val productsState by viewModel.productsState.collectAsState()

    when (productsState) {
        is ProductsState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is ProductsState.Success -> {
            val products = (productsState as ProductsState.Success).products

            var selectedTabIndex by remember { mutableIntStateOf(0) }

            Column {

                // Custom TabRow with SectionsRow style
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    products.forEachIndexed { index, product ->
                        Text(
                            text = product.name ?: "Unknown",
                            modifier = Modifier
                                .background(
                                    color = Color.Transparent,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(8.dp)
                                .clickable { selectedTabIndex = index },
                            color = if (selectedTabIndex == index) Color.Cyan else Color.Gray,
                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal
                        )

                        if (index < products.size - 1) {
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }

                products.getOrNull(selectedTabIndex)?.let { product ->
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        items(product.data) { dataItem ->
                            CustomProductItem(dataItem)
                        }
                    }
                }
            }
        }
        is ProductsState.Error -> {
            val message = (productsState as ProductsState.Error).message

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = message, color = Color.Red, fontWeight = FontWeight.Bold)
            }
        }
    }
}
@Composable
fun CustomProductItem(dataItem: DataItem) {
    val imageUrl = getFullImageUrl(dataItem.picture)
    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )

            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Favorite",
                tint = Color.Gray,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp)
                    .size(16.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${dataItem.price.firstOrNull()?.price ?: 0.0} ₽",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )

            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Add to cart",
                tint = Color.Green,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

fun getFullImageUrl(relativePath: String?): String? {
    return relativePath?.let { "https://szorin.vodovoz.ru$it" }
}
