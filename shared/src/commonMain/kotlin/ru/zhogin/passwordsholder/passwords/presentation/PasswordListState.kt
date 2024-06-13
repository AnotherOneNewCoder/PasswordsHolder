package ru.zhogin.passwordsholder.passwords.presentation

import ru.zhogin.passwordsholder.passwords.domain.Password
import ru.zhogin.passwordsholder.passwords.domain.Type

data class PasswordListState(
    val password: List<Password> = emptyList(),
    val selectedPassword: Password? = null,
    val isAddPasswordSheetOpen: Boolean = false,
    val isSelectedPasswordSheepOpen: Boolean = false,
    val validationTypeError: Type? = null,
    val validationNameError: String? = null,
    val validationLoginError: String? = null,
    val validationPassError: String? = null,
)
