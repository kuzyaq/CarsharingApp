package com.example.carsharing.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carsharing.R
import com.example.carsharing.domain.navItem
import com.example.carsharing.ui.theme.Background
import com.example.carsharing.ui.theme.Blue10
import com.example.carsharing.ui.theme.Gray10

@Composable
fun MainScreen(){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Background,
        bottomBar = { NavBar() }
    )
    { innerPadding ->
        Column (
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ){
                SearchField()
                ProfileIcon()
            }
            Row (
                Modifier.fillMaxWidth()
                .padding(start = 36.dp, end = 36.dp, top = 48.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Brands",
                    style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Medium)
                )
                Text(
                    text = "See All"
                )
            }
            LazyRow (
                modifier = Modifier.padding(start = 30.dp, top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ){
                item { BrandCard() }
                item { BrandCard() }
                item { BrandCard() }
                item { BrandCard() }
            }
            Spacer(Modifier.height(35.dp))
            Text(
                text = "Popular cars",
                style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Medium),
                modifier = Modifier.align(Alignment.Start)
                    .padding(start = 36.dp)
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                item { Spacer(Modifier.height(1.dp)) }
                item { CarCard() }
                item { CarCard() }
                item { CarCard() }
                item { CarCard() }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchField(){
    var searchText by remember {
        mutableStateOf("")
    }
    var isActive by remember {
        mutableStateOf(false)
    }
    SearchBar(
        modifier = Modifier.size(width = 250.dp, height = 48.dp)
            .offset(x = 8.dp),
        query = searchText,
        onQueryChange = { text ->
            searchText = text
        },
        onSearch = { text ->
            isActive = false
        },
        active = isActive,
        onActiveChange = {
            isActive = it
        },
        placeholder = {
            Text(
                text = "Search",
                style = TextStyle(fontSize = 14.sp)
            )
        },
        colors = SearchBarDefaults.colors(Color.White),
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.search_icon),
                contentDescription = "Search",
                modifier = Modifier.padding(start = 20.dp, end = 8.dp)
            )
        }
    ) { }
}

@Composable
private fun NavBar(){
    val navItemList = listOf(
        navItem("Home", R.drawable.home_icon2),
        navItem("Navigation", R.drawable.discovery_icon),
        navItem("Profile", R.drawable.profile_icon)
    )

    var selectedIndex by remember {
        mutableStateOf(0)
    }
    NavigationBar(
        containerColor = Color.Transparent,
        contentColor = Blue10
    ) {
        navItemList.forEachIndexed{index, navItem ->
            NavigationBarItem(
                selected = false,
                onClick = {
                    selectedIndex = index
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(navItem.icon),
                        contentDescription = navItem.label,
                        tint = if (selectedIndex == index) Blue10
                        else Gray10
                    )
                },
            )

        }
    }
}

@Composable
fun ProfileIcon(){
    Image(
        painter = painterResource(R.drawable.img),
        contentDescription = "Profile image",
        modifier = Modifier.size(48.dp)
            .clip(RoundedCornerShape(20.dp)),
        alignment = Alignment.TopEnd
    )
}

@Composable
private fun BrandCard(){
    Card(modifier = Modifier.size(width = 87.dp, height = 116.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(start = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.img_1), // Img
                contentDescription = "CarCard",
                modifier = Modifier.size(width = 66.dp, height = 50.dp)
                    .weight(1f)
            )
            Text(
                text = "Mercedes", // Brand
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier
                    .weight(0.5f)
            )
            Text(
                text = "+32", // Count
                style = TextStyle(fontSize = 14.sp, color = Blue10),
                modifier = Modifier
                    .weight(0.5f)
            )
        }
    }
}

@Composable
private fun CarCard() {
    Box{
        Card(
            modifier = Modifier
                .width(311.dp)
                .height(171.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "GLA 250 SUV",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Start)
                        .padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = "Automatic", color = Color.Gray)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "7 seats", color = Color.Gray)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Diesel", color = Color.Gray)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        modifier = Modifier.size(width = 101.dp, height = 36.dp),
                        onClick = { /* TODO */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Blue10
                        ),
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(start = 15.dp, end = 15.dp, top = 8.dp, bottom = 8.dp)
                    ) {
                        Text(
                            modifier = Modifier.fillMaxSize(),
                            text = "Rent Now",
                            fontSize = 16.sp,
                            color = Color.White,
                        )

                    }
                    Button(
                        onClick = { /* TODO */ },
                        modifier = Modifier.size(width = 101.dp, height = 36.dp),
                        colors = ButtonDefaults.buttonColors(
                            Color.Transparent
                        ),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, Blue10)
                    ) {
                        Text(text = "Detail", color = Blue10)
                    }
                }
            }
        }
        Image(
            painter = painterResource(R.drawable.img_2),
            contentDescription = null,
            modifier = Modifier.offset(x = 140.dp, y = -70.dp)
                .size(width = 203.dp, height = 164.dp)
        )
    }
}

