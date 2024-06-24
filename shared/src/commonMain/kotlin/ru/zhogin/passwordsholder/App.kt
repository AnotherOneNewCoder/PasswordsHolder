package ru.zhogin.passwordsholder

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import ru.zhogin.passwordsholder.calculator.CalculatorViewModel
import ru.zhogin.passwordsholder.calculator.ui.CalculatorScreen
import ru.zhogin.passwordsholder.core.presentation.AppTheme
import ru.zhogin.passwordsholder.core.presentation.ImagePicker
import ru.zhogin.passwordsholder.di.AppModule
import ru.zhogin.passwordsholder.passwords.presentation.ComposableScreens
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

        val calculatorViewModel = getViewModel(
            key = "calculator-screen",
            factory = viewModelFactory {
                CalculatorViewModel(appModule.passwordDataSource)
            }
        )
        val calculatorState by calculatorViewModel.state.collectAsState()

        val navController = rememberNavController()

        NavHost(navController, startDestination = ComposableScreens.CalculatorScreen.route) {
            composable(ComposableScreens.CalculatorScreen.route) {
                CalculatorScreen(
                    calculatorState = calculatorState,
                    onAction = calculatorViewModel::onAction,
                    onNavPassScreen = {
                        navController.navigate(ComposableScreens.PasswordScreen.route)
                    }
                )
            }
            composable(ComposableScreens.PasswordScreen.route) {
                PasswordsListScreen(
                    state = state,
                    newPassword = viewModel.newPassword,
                    onEvent = viewModel::onEvent,
                    imagePicker = imagePicker,
                )
            }
        }
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background,
//        ) {
//            CalculatorScreen(
//                calculatorState = calculatorState,
//                onAction = calculatorViewModel::onAction,
//
//            )
////            PasswordsListScreen(
////                state = state,
////                newPassword = viewModel.newPassword,
////                onEvent = viewModel::onEvent,
////                imagePicker = imagePicker
////            )
//        }
    }
}