package ru.zhogin.passwordsholder.core.presentation

import androidx.compose.runtime.Composable

expect class ImagePicker {
    @Composable
    fun RegisterPicker(onImagePicked: (ByteArray) -> Unit)

    fun pickImage()
}