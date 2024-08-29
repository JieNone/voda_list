package ru.tyurin.vodovoz_test.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun PlaceholderScreen() {
    Column(modifier = Modifier.fillMaxSize()) {

        SearchBar()

        CustomProductsScreen()

        BrandsSection()

        OtherSections()

        BottomNavigationBar()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = { Text(text = "Искать на Vodovoz") },
        leadingIcon = {
            Icon(
                imageVector = Default.Search,
                contentDescription = "Search",
                tint = Color.Gray
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Default.ShoppingCart,
                contentDescription = "Scan",
                tint = Color.Gray
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
    )
}


@Composable
fun BrandsSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Бренды",
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        LazyRow(modifier = Modifier.padding(vertical = 8.dp)) {
            items(4) {
                BrandItem()
            }
        }
    }
}

@Composable
fun BrandItem() {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            .size(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Brand", color = Color.Gray)
    }
}

@Composable
fun OtherSections() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Просмотренное", fontWeight = FontWeight.Bold, color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Оцените ваши покупки", color = Color.Gray)
        }
    }
}

@Composable
fun BottomNavigationBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        BottomNavItem("Главная", Default.Home)
        BottomNavItem("Каталог", Icons.AutoMirrored.Filled.List)
        BottomNavItem("Избранное", Default.Favorite)
        BottomNavItem("Профиль", Default.Person)
    }
}

@Composable
fun BottomNavItem(label: String, icon: ImageVector) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(imageVector = icon, contentDescription = label, tint = Color.Gray)
        Text(text = label, color = Color.Gray)
    }
}
