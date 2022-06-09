package com.mgb.color_wheel.ui

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mgb.color_wheel.ui.theme.SDGreen
import com.mgb.color_wheel.ui.theme.SDOrange
import com.mgb.color_wheel.ui.theme.SDTeal

class ContentViewModel : ViewModel() {
    private val _selectedPosition = MutableLiveData(0)
    private val _selectedColors = MutableLiveData(mutableListOf(SDTeal, SDGreen, SDOrange))
    private val _selectedColor = MutableLiveData(SDTeal)

    val selectedPosition: LiveData<Int>
        get() = _selectedPosition

    val selectedColors: LiveData<MutableList<Color>>
        get() = _selectedColors

    val selectedColor: LiveData<Color>
        get() = _selectedColor

    fun updateSelectedColor(position: Int) {
        _selectedPosition.value = position
    }

    fun updateColor(color: Color) {
        val colors = mutableListOf<Color>()
        colors.addAll(_selectedColors.value ?: return)
        val position = _selectedPosition.value ?: return

        colors[position] = color

        _selectedColors.value = colors
        _selectedColor.value = color
    }
}
