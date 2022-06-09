package com.mgb.color_wheel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.mgb.color_wheel.ui.ContentViewModel
import com.mgb.color_wheel.ui.theme.Color_wheelTheme
import com.mgb.color_wheel.ui.theme.SDBackground
import com.mgb.color_wheel.ui.views.ColorWheel
import com.mgb.color_wheel.ui.views.SegmentedControl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Color_wheelTheme {
                MainView()
            }
        }
    }
}

@Composable
fun MainView() {
    val viewModel = ContentViewModel()

    Column(
        modifier = Modifier.fillMaxSize().background(SDBackground),
    ) {
        SegmentedControl(viewModel)

        ColorWheel(viewModel)
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun DefaultPreview() {
    Color_wheelTheme {
        MainView()
    }
}