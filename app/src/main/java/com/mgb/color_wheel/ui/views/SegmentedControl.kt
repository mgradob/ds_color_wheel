package com.mgb.color_wheel.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.mgb.color_wheel.ui.ContentViewModel
import com.mgb.color_wheel.ui.theme.Dimensions
import com.mgb.color_wheel.ui.theme.SDElementBackground
import com.mgb.color_wheel.ui.theme.SDElementSelected

@Composable
fun SegmentedControl(viewModel: ContentViewModel) {
    val selectedColor = viewModel.selectedPosition.observeAsState()
    val colors = viewModel.selectedColors.observeAsState()

    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
    ) {
        colors.value?.forEachIndexed { index, color ->
            SegmentedControlItem(color, index == selectedColor.value) {
                viewModel.updateSelectedColor(index)
            }
        }
    }
}

@Composable
private fun SegmentedControlItem(color: Color, selected: Boolean = false, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .background(if (selected) SDElementSelected else SDElementBackground)
            .padding(Dimensions.SegmentedControlItemPadding)
            .clickable { onClick.invoke() }
    ) {
        Box(
            modifier = Modifier
                .background(color, shape = CircleShape)
                .size(Dimensions.SegmentedControlItemIndicator)
        ) { }
    }
}

@Preview
@Composable
private fun PreviewSegmentedControl() {
    SegmentedControl(ContentViewModel())
}