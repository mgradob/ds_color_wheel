package com.mgb.color_wheel.ui.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toOffset
import com.mgb.color_wheel.ui.ContentViewModel
import com.mgb.color_wheel.ui.theme.Dimensions
import com.mgb.color_wheel.ui.theme.SDTeal
import com.mgb.color_wheel.ui.utils.toDegrees
import kotlin.math.atan2
import kotlin.math.hypot

@OptIn(ExperimentalGraphicsApi::class)
@Composable
fun ColorWheel(viewModel: ContentViewModel) {
    var wheelCenter by remember { mutableStateOf(IntOffset.Zero) }
    var wheelRadius by remember { mutableStateOf(0) }

    val selectedColor = viewModel.selectedColor.observeAsState()
    var selectedColorCenter by remember { mutableStateOf(IntOffset.Zero)}

    Box {
        Canvas(
            modifier = Modifier
                .padding(Dimensions.ContentPadding)
                .fillMaxSize(fraction = 0.99F)
                .onGloballyPositioned { coordinates ->
                    wheelCenter = coordinates.size.center
                    wheelRadius = coordinates.size.width / 2
                }
                .pointerInput("color_wheel") {
                    detectTapGestures { offset ->
                        selectedColorCenter = IntOffset(offset.x.toInt(), offset.y.toInt())

                        val ox = wheelCenter.x.toFloat()
                        val oy = wheelCenter.y.toFloat()
                        val dx = offset.x - ox
                        val dy = offset.y - oy

                        val h = minOf(hypot(dx, dy), hypot(ox, oy))

                        val hue = (atan2(dx, dy).toDegrees() + 360) % 360
                        val sat = h / wheelRadius

                        val color = Color.hsv(hue.toFloat(), minOf(sat, 1F), 1F)
                        viewModel.updateColor(color)
                    }
                }
        ) {
            val colors = listOf(
                Color.Red,
                Color.Yellow,
                Color.Green,
                Color.Cyan,
                Color.Blue,
                Color.Magenta,
                Color.Red,
            )

            drawCircle(
                Brush.sweepGradient(colors, center = wheelCenter.toOffset())
            )
            drawCircle(
                Brush.radialGradient(
                    listOf(Color.White, Color.Transparent),
                    radius = wheelRadius.toFloat()
                )
            )
        }

        ColorWheelHandle(color = selectedColor.value ?: SDTeal, offset = selectedColorCenter)
    }
}

@Composable
private fun ColorWheelHandle(color: Color, offset: IntOffset) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .offset(offset.x.dp, offset.y.dp)
            .background(color = color)
            .clip(CircleShape)
            .border(3.dp, color = Color.White, shape = CircleShape)
            .shadow(3.dp)
    )
}

@Composable
@Preview
private fun PreviewColorWheel() {
    ColorWheel(ContentViewModel())
}