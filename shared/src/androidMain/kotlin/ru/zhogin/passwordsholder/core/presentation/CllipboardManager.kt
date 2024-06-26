package ru.zhogin.passwordsholder.core.presentation

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun ClipboardManager(text: String) {
    val context = LocalContext.current
    val clipboardManager = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
    // clip data is initialized with the text variable declared above
    val clipData: ClipData = ClipData.newPlainText("text", text)

    // Clipboard saves this clip object
    clipboardManager.setPrimaryClip(clipData)

    // A toast is shown for user reference that
    // the text is copied to the clipboard
    Toast.makeText(context, "Copied to Clipboard", Toast.LENGTH_SHORT).show()
}