package com.example.carsharing.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
    Box (
        Modifier.fillMaxSize()
    ){
        Map()

    }

}

@Composable
@Preview
fun preview() {
    Map()
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