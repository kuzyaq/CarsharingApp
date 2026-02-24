package com.example.carsharing.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carsharing.R
import com.example.carsharing.domain.navItem
import com.example.carsharing.ui.theme.Background
import com.example.carsharing.ui.theme.Blue10
import com.example.carsharing.ui.theme.Grey10


@Composable
fun CityDropdownMenu() {
    var expanded by remember { mutableStateOf(false) }
    var city by remember { mutableStateOf("Москва") }
    Row (
        modifier = Modifier.clickable(){
            expanded = !expanded
        },
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = city,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
        )
        Spacer(modifier = Modifier.width(2.dp))
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_down),
            contentDescription = "C",
            modifier = Modifier
                .size(16.dp)
                .padding(top = 3.dp),
            tint = Color.Black
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = Background
        ) {
            DropdownMenuItem(
                text = { Text("Санкт-Петербург") },
                onClick = {
                    city = "Санкт-Петербург"
                    expanded = !expanded
                }
            )
            DropdownMenuItem(
                text = { Text("Казань") },
                onClick = {
                    city = "Казань"
                    expanded = !expanded
                }
            )
        }
    }
}

@Composable
fun MainScreen(
    onCarClick: (String) -> Unit,
    onProfileClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Background,
        bottomBar = { NavBar(onProfileClick = onProfileClick) }
    )
    { innerPadding ->
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(48.dp)
            ) {
                IconButton(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Background, RoundedCornerShape(16.dp))
                        .border(
                            border = BorderStroke(1.3.dp, Color.Gray),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    onClick = {},
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_notification),
                        contentDescription = "Notifications",
                        modifier = Modifier.fillMaxSize(),
                        tint = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Ваше местоположение",
                        style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Light)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    CityDropdownMenu()
                }

                IconButton(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Background, RoundedCornerShape(16.dp))
                        .border(
                            border = BorderStroke(1.3.dp, Color.Gray),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(10.dp),
                    onClick = {},
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.profile_icon),
                        contentDescription = "Notifications",
                        modifier = Modifier.fillMaxSize(),
                        tint = Color.Gray
                    )
                }
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                text = "Найдите автомобиль своей мечты.",
                style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Medium)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(Color.White),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SearchField()
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 36.dp, end = 36.dp, top = 28.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Марки",
                        style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Medium)
                    )
                    Text(
                        text = "Посмотреть все"
                    )
                }
                LazyRow(
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item { BrandCard() }
                    item { BrandCard() }
                    item { BrandCard() }
                    item { BrandCard() }
                }
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "Популярные авто",
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Medium),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 36.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                LazyRow(
                    modifier = Modifier.padding(start = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    item {
                        CarCard(
                            carImageId = R.drawable.lixiang_l7,
                            carLabel = "Lixiang L7",
                            transmissionType = "АКПП",
                            cost = 12000,
                            onClick = { onCarClick("lixiang_l7") }
                        )
                    }
                    item {
                        CarCard(
                            carImageId = R.drawable.mercedes_s450_coupe,
                            carLabel = "Mercedes S450 coupe",
                            transmissionType = "АКПП",
                            cost = 7000,
                            onClick = { onCarClick("mercedes_s450_coupe") }
                        )
                    }
                    item {
                        CarCard(
                            carImageId = R.drawable.dodge_challenger,
                            carLabel = "Dodge Challenger",
                            transmissionType = "АКПП",
                            cost = 6000,
                            onClick = { onCarClick("dodge_challenger") }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchField() {
    var searchText by remember {
        mutableStateOf("")
    }
    var isActive by remember {
        mutableStateOf(false)
    }
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        query = searchText,
        shadowElevation = 4.dp,
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
                text = "Поиск",
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
private fun NavBar(
    onProfileClick: () -> Unit
) {
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
        navItemList.forEachIndexed { index, navItem ->
            NavigationBarItem(
                selected = false,
                onClick = {
                    selectedIndex = index
                    if (navItem.label == "Profile") {
                        onProfileClick()
                    }
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(navItem.icon),
                        contentDescription = navItem.label,
                        tint = if (selectedIndex == index) Blue10
                        else Grey10
                    )
                },
            )

        }
    }
}

@Composable
fun ProfileIcon() {
    Image(
        painter = painterResource(R.drawable.img),
        contentDescription = "Profile image",
        modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(20.dp)),
        alignment = Alignment.TopEnd
    )
}

@Composable
private fun BrandCard() {
    Card(
        modifier = Modifier.size(width = 96.dp, height = 96.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.img_1), // Img
                contentDescription = "CarCard",
                modifier = Modifier
                    .size(width = 64.dp, height = 64.dp)
                    .weight(1f)
            )
            Text(
                text = "Mercedes", // Brand
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier
                    .weight(0.5f)
            )
        }
    }
}

@Composable
private fun CarCard(
    carImageId: Int,
    carLabel: String,
    transmissionType: String,
    cost: Int,
    onClick: () -> Unit
) {
    Box {
        Card(
            modifier = Modifier
                .width(332.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
                Column(
                modifier = Modifier
                        .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(carImageId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = carLabel,
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row {
                    Text(
                        text = transmissionType,
                        style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Light)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = cost.toString() + " ₽/сут.",
                        style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Light)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = onClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue10
                    ),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Text(
                        text = "Забронировать",
                        fontSize = 16.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }


            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                Image(
//                     painter = painterResource(R.drawable.img_2),
//                        contentDescription = null,
//             modifier = Modifier.offset(x = 140.dp, y = -70.dp)
//                .size(width = 203.dp, height = 164.dp)
//                 )


//
                //
                //                Text(
//                    text = "GLA 250 SUV",
//                    fontSize = 21.sp,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.align(Alignment.Start)
//                        .padding(top = 8.dp)
//                )
//                Spacer(modifier = Modifier.height(30.dp))
//                Row (
//                    verticalAlignment = Alignment.CenterVertically
//                ){
//                    Text(text = "Automatic", color = Color.Gray)
//                    Spacer(modifier = Modifier.width(16.dp))
//                    Text(text = "7 seats", color = Color.Gray)
//                    Spacer(modifier = Modifier.width(16.dp))
//                    Text(text = "Diesel", color = Color.Gray)
//                }
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp, vertical = 8.dp),
//                    horizontalArrangement = Arrangement.SpaceAround
//                ) {
//                    Button(
//                        modifier = Modifier.size(width = 101.dp, height = 36.dp),
//                        onClick = { /* TODO */ },
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = Blue10
//                        ),
//                        shape = RoundedCornerShape(10.dp),
//                        contentPadding = PaddingValues(start = 15.dp, end = 15.dp, top = 8.dp, bottom = 8.dp)
//                    ) {
//                        Text(
//                            modifier = Modifier.fillMaxSize(),
//                            text = "Rent Now",
//                            fontSize = 16.sp,
//                            color = Color.White,
//                        )
//
//                    }
//                    Button(
//                        onClick = { /* TODO */ },
//                        modifier = Modifier.size(width = 101.dp, height = 36.dp),
//                        colors = ButtonDefaults.buttonColors(
//                            Color.Transparent
//                        ),
//                        shape = RoundedCornerShape(10.dp),
//                        border = BorderStroke(1.dp, Blue10)
//                    ) {
//                        Text(text = "Detail", color = Blue10)
//                    }
//                }
//            }
//        }
//        Image(
//            painter = painterResource(R.drawable.img_2),
//            contentDescription = null,
//            modifier = Modifier.offset(x = 140.dp, y = -70.dp)
//                .size(width = 203.dp, height = 164.dp)
//        )
            }
        }
    }
}

