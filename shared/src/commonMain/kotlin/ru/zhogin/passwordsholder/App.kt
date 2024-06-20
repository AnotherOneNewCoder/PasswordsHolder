package ru.zhogin.passwordsholder

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import ru.zhogin.passwordsholder.core.presentation.AppTheme
import ru.zhogin.passwordsholder.core.presentation.ImagePicker
import ru.zhogin.passwordsholder.di.AppModule
import ru.zhogin.passwordsholder.passwords.presentation.PasswordListViewModel
import ru.zhogin.passwordsholder.passwords.presentation.PasswordsListScreen

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    appModule: AppModule,
    imagePicker: ImagePicker,
) {
    AppTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
    ) {
        val viewModel = getViewModel(
            key = "password-list-screen",
            factory = viewModelFactory {
                PasswordListViewModel(appModule.passwordDataSource)
            }
        )
        val state by viewModel.state.collectAsState()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            PasswordsListScreen(
                state = state,
                newPassword = viewModel.newPassword,
                onEvent = viewModel::onEvent,
                imagePicker = imagePicker
            )
        }
    }
}