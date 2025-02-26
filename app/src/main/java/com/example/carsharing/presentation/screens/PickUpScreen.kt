package com.example.carsharing.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carsharing.R
import com.example.carsharing.ui.theme.Blue10
import com.example.carsharing.ui.theme.Gray10
import ru.sulgik.mapkit.Animation
import ru.sulgik.mapkit.MapKit
import ru.sulgik.mapkit.compose.MapControllerEffect
import ru.sulgik.mapkit.compose.YandexMap
import ru.sulgik.mapkit.compose.bindToLifecycleOwner
import ru.sulgik.mapkit.compose.rememberAndInitializeMapKit
import ru.sulgik.mapkit.compose.rememberCameraPositionState
import ru.sulgik.mapkit.compose.rememberYandexMapController
import ru.sulgik.mapkit.geometry.Point
import ru.sulgik.mapkit.map.CameraPosition
import ru.sulgik.mapkit.map.MapWindow
import kotlin.time.Duration

fun initMapKit() {
    MapKit.setApiKey("93663deb-9ac3-4bd8-aea0-b9c693a40d71")
}

@Composable
fun PickUpScreen() {
    Box(
        Modifier.fillMaxSize()
    ) {
        Map()
        Column {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 50.dp)
            ){
                ReturnFAB()
                Spacer(modifier = Modifier.weight(1f))
                Column {
                    ProfileIcon()
                    Spacer(Modifier.height(12.dp))
                    FloatingActionButton(
                        onClick = {},
                        containerColor = Color.White,
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.size(48.dp),
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_hzkaknazvat),
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                }
            }
            Spacer(Modifier.weight(1f))
            CarCard()
        }
    }
}

@Composable
@Preview
fun preview() {
    CarCard()
}

@Composable
private fun Map() {
    val startPosition = CameraPosition(
        target = Point(55.704205, 37.507699),
        zoom = 16.5f,
        azimuth = 0f,
        tilt = 0f,
    )

    rememberAndInitializeMapKit().bindToLifecycleOwner()
    val mapController = rememberYandexMapController()
    MapControllerEffect(mapController) { mapWindow: MapWindow ->
        mapWindow.map.move(startPosition, Animation(Animation.Type.SMOOTH, Duration.ZERO))
        mapWindow.map.isZoomGesturesEnabled = true


    }
    YandexMap(
        controller = mapController,
        modifier = Modifier.fillMaxSize()
    )

}

@Composable
private fun CarCard() {
    Box {
        Card(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .padding(horizontal = 30.dp, vertical = 40.dp),
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
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Automatic", color = Gray10, fontSize = 12.sp)
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = "7 seats", color = Gray10, fontSize = 12.sp)
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = "Diesel", color = Gray10, fontSize = 12.sp)
                    }
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        Row (
                            verticalAlignment = Alignment.Bottom
                        ){
                            Text(
                                text = "$400",
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Medium,
                            )
                            Text(
                                modifier = Modifier.padding(bottom = 1.dp, start = 4.dp),
                                text = "per day",
                                fontSize = 12.sp,
                                color = Gray10
                            )
                        }
                        Row (
                            verticalAlignment = Alignment.Bottom
                        ){
                            Text(
                                text = "$80",
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Medium,
                            )
                            Text(
                                modifier = Modifier.padding(bottom = 1.dp, start = 4.dp),
                                text = "per hour",
                                fontSize = 12.sp,
                                color = Gray10
                            )
                        }
                    }
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { /* TODO */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Blue10
                        ),
                        shape = RoundedCornerShape(10.dp),
                    ) {
                        Text(
                            text = "See More",
                            fontSize = 16.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )

                    }
                }
            }
        }
        Image(
            painter = painterResource(R.drawable.img_2),
            contentDescription = null,
            modifier = Modifier
                .offset(x = 170.dp, y = -30.dp)
                .size(width = 203.dp, height = 164.dp)
        )
    }
}