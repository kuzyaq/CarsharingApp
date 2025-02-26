package com.example.carsharing.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carsharing.R
import com.example.carsharing.ui.theme.Background
import com.example.carsharing.ui.theme.Blue10
import com.example.carsharing.ui.theme.Blue20
import com.example.carsharing.ui.theme.Gray10
import com.example.carsharing.ui.theme.Yellow1


@Composable
fun CarPageScreen(){
    Box(
        Modifier
            .background(Color.White)
            .fillMaxSize()
    ){
        Column {
            CarPhotoCard("S 500 Sedan", "4.9", "230", R.drawable.img_3)
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Specs",
                fontSize = 21.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(start = 20.dp)
            )
            Spacer(Modifier.height(16.dp))
            LazyRow(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                item {
                    SpecsCard("Power", "429 hp @ 6,100 rpm")
                }
                item {
                    SpecsCard("Max Speed", "280 km/h")
                }
                item {
                    SpecsCard("Acceleration", "4.9 sec 0-60 mph")
                }
            }
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Plan",
                fontSize = 21.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(start = 20.dp)
            )
            Spacer(Modifier.height(16.dp))
            LazyRow(
                Modifier.fillMaxWidth()
                    .padding(start = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item { 
                    PlanCard(80, R.drawable.ic_clock, Blue10, Blue20)
                }
                item {
                    PlanCard(80, R.drawable.ic_calendar, Gray10, Background)
                }
            }
            Spacer(Modifier.height(24.dp))
            Text(
                text = "Location",
                fontSize = 21.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(start = 20.dp)
            )
            Spacer(Modifier.height(16.dp))
            LocationCard("200-298 Clipper St San Francisco, CA 94114, USA")
            Spacer(Modifier.height(20.dp))
            PickUp()
        }
    }
}

@Composable
@Preview
private fun Preview(){
    //CarPhotoCard("S 500 Sedan", "4.9", "230")
    //PlanCard(80, R.drawable.ic_clock, Blue10)
    //SpecsCard("Power", "429 hp @ 6,100 rpm")
    //LocationCard("200-298 Clipper St San Francisco, CA 94114, USA")
    //PickUp()
    //CarPageScreen()

}

@Composable
fun ReturnFAB(){
    FloatingActionButton(
        onClick = {},
        containerColor = Color.White,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.size(48.dp),
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_arrow),
            contentDescription = null,
            tint = Color.Black
        )
    }
}

@Composable
private fun CarPhotoCard(carModel: String, carRating: String, reviewsCount: String, carImage: Int){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(375.dp),
        shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp),
        colors = CardDefaults.cardColors(containerColor = Background)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                ReturnFAB()
                Spacer(
                    modifier = Modifier.weight(1f)
                        .size(10.dp)
                )
                FloatingActionButton(
                    onClick = {},
                    containerColor = Color.White,
                    modifier = Modifier.size(48.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_favourite),
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
            Spacer(Modifier.height(4.dp))
            Text(
                text = carModel,
                modifier = Modifier
                    .padding(start = 20.dp),
                fontWeight = FontWeight.Medium,
                fontSize = 28.sp
            )
            Spacer(Modifier.height(8.dp))
            Row (
                Modifier.padding(start = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(R.drawable.ic_star),
                    contentDescription = null,
                    tint = Yellow1
                )
                Text(
                    text = carRating,
                    fontSize = 16.sp
                )
                Text(
                    text = "($reviewsCount Reviews)",
                    fontSize = 14.sp,
                    color = Gray10
                )
            }
            Image(
                painter = painterResource(carImage),
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentScale = ContentScale.Crop
            )
        }


    }
}

@Composable
private fun PlanCard(cost: Int, iconId: Int, color: Color, secColor: Color){
    Card(
        modifier = Modifier
            .size(width = 180.dp, height = 80.dp),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(2.5.dp, color)
    ) {
        Row {
            Column (
                modifier = Modifier
                    .fillMaxHeight()
                    .background(secColor, shape = RectangleShape)
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Icon(
                    painter = painterResource(iconId),
                    contentDescription = null,
                    tint = color
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "$$cost",
                    fontSize = 14.sp,
                    color = color,
                    fontWeight = FontWeight.Medium
                )
            }
            Column(
                Modifier
                    .background(Color.White, RectangleShape)
                    .fillMaxHeight()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Hourly Rent",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = color
                )
                Text(
                    text = "Best for business appointments",
                    fontSize = 14.sp,
                    color = Gray10
                )
            }
        }

    }
}

@Composable
private fun SpecsCard(spec: String, info: String){
    Card(
        modifier = Modifier
            .size(width = 124.dp, height = 48.dp),
        border = BorderStroke(1.dp, Gray10),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,

        ) {
            Text(
                text = spec,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = info,
                fontSize = 12.sp,
                fontWeight = FontWeight.Thin
            )
        }
    }
}

@Composable
private fun LocationCard(location: String){
    Card(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .height(40.dp),
        border = BorderStroke(1.dp, Gray10),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row (
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Icon(
                painter = painterResource(R.drawable.ic_location),
                contentDescription = null,
                tint = Color.Black
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = location,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun PickUp(){
    Row(
        Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "$80",
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "/ hour",
                fontSize = 21.sp,
                color = Gray10
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier.size(width = 150.dp, height = 44.dp),
            onClick = { /* TODO */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue10
            ),
            shape = RoundedCornerShape(10.dp),
            contentPadding = PaddingValues(start = 15.dp, end = 15.dp, top = 8.dp, bottom = 8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Pick Up",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.width(6.dp))
                Icon(
                    modifier = Modifier
                        .size(20.dp),
                    painter = painterResource(R.drawable.ic_arrow_right_2),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}



