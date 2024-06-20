package ru.zhogin.passwordsholder.core.presentation

import androidx.compose.runtime.Composable

expect class ImagePickerFactory {
    @Composable
    fun createPicker() : ImagePicker
}