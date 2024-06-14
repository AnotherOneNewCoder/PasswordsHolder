package ru.zhogin.passwordsholder.passwords.presentation

import ru.zhogin.passwordsholder.passwords.domain.Password
import ru.zhogin.passwordsholder.passwords.domain.Type

sealed interface PasswordListEvent {
    object OnAddNewPasswordClick: PasswordListEvent
    object DismissPassword: PasswordListEvent
    data class OnTypeChanged(val value: Type): PasswordListEvent
    data class OnNameChanged(val value: String): PasswordListEvent
    data class OnLoginChanged(val value: String): PasswordListEvent
    data class OnPassChanged(val value: String): PasswordListEvent
    class OnPhotoClicked(val bytes: ByteArray): PasswordListEvent
    object AddPhotoClicked: PasswordListEvent
    object SavePassword: PasswordListEvent
    data class SelectPassword(val password: Password): PasswordListEvent
    data class EditPassword(val password: Password): PasswordListEvent
    object DeletePassword: PasswordListEvent
}