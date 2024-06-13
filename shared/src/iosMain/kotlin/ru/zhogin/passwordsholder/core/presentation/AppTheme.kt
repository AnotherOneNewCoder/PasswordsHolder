package ru.zhogin.passwordsholder.core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import ru.zhogin.passwordsholder.ui.theme.Typography
import ru.zhogin.passwordsholder.ui.theme.highContrastDarkColorScheme
import ru.zhogin.passwordsholder.ui.theme.highContrastLightColorScheme

@Composable
actual fun AppTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if(darkTheme) highContrastDarkColorScheme else highContrastLightColorScheme,
        typography = Typography,
        content = content
    )
}