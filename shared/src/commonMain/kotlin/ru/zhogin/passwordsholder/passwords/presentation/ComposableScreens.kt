package ru.zhogin.passwordsholder.passwords.presentation

sealed class ComposableScreens(
    val route: String
) {
    data object CalculatorScreen : ComposableScreens("calculator_screen")
    data object PasswordScreen : ComposableScreens("password_screen")
}