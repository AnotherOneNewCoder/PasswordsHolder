package ru.zhogin.passwordsholder.passwords.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.zhogin.passwordsholder.passwords.domain.Password
import ru.zhogin.passwordsholder.passwords.domain.PasswordDataSource
import ru.zhogin.passwordsholder.passwords.domain.PasswordValidator
import ru.zhogin.passwordsholder.passwords.domain.Type

class PasswordListViewModel(
    private val passwordDataSource: PasswordDataSource,
): ViewModel() {
    private val _state = MutableStateFlow(PasswordListState())
    val state = combine(
        _state,
        passwordDataSource.getPasswords()
    ){ state, passwords ->
        state.copy(
            password = passwords
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), PasswordListState())

    var newPassword: Password? by mutableStateOf(null)
        private set
    fun onEvent(event: PasswordListEvent) {
        when(event) {

            PasswordListEvent.DeletePassword -> {
                viewModelScope.launch {
                    _state.value.selectedPassword?.id?.let { id ->
                        _state.update {
                            it.copy(
                                isSelectedPasswordSheepOpen = false
                            )
                        }
                        passwordDataSource.deletePassword(id)
                        delay(500L) // animation delay
                        _state.update {
                            it.copy(
                                selectedPassword = null
                            )
                        }
                    }
                }
            }
            PasswordListEvent.DismissPassword -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            isSelectedPasswordSheepOpen = false,
                            isAddPasswordSheetOpen = false,
                            validationNameError = null,
                            validationPassError = null,
                            validationTypeError = null,
                            validationLoginError = null,
                        )
                    }
                    delay(500L) // animation delay
                    newPassword = null
                    _state.update {
                        it.copy(selectedPassword = null)
                    }
                }
            }
            is PasswordListEvent.EditPassword -> {
                _state.update { it.copy(
                    selectedPassword = null,
                    isAddPasswordSheetOpen = true,
                    isSelectedPasswordSheepOpen = false,
                ) }
                newPassword = event.password
            }
            PasswordListEvent.OnAddNewPasswordClick -> {
                _state.update { it.copy(
                    isAddPasswordSheetOpen = true
                ) }
                newPassword = emptyPassword
            }
            is PasswordListEvent.OnLoginChanged -> {
                newPassword = newPassword?.copy(
                    login = event.value
                )
            }
            is PasswordListEvent.OnNameChanged -> {
                newPassword = newPassword?.copy(
                    name = event.value
                )
            }
            is PasswordListEvent.OnPassChanged -> {
                newPassword = newPassword?.copy(
                    pass = event.value
                )
            }
            is PasswordListEvent.OnPhotoClicked -> {
                newPassword = newPassword?.copy(
                    photoBytes = event.bytes
                )
            }
            is PasswordListEvent.OnTypeChanged -> {
                newPassword = newPassword?.copy(
                    type = event.value
                )
            }
            PasswordListEvent.SavePassword -> {
                newPassword?.let { password ->
                    val result = PasswordValidator.validatePassword(password)
                    val errors = listOfNotNull(
                        result.nameError,
                        result.loginError,
                        result.passError,
                    )
                    if (errors.isEmpty()) {
                        _state.update { it.copy(
                            isAddPasswordSheetOpen = false,
                            validationNameError = null,
                            validationLoginError = null,
                            validationPassError = null,
                            validationTypeError = null,
                        ) }
                        viewModelScope.launch {
                            passwordDataSource.insertPassword(password)
                            delay(500L) // animation delay
                            newPassword = null
                        }
                    } else {
                        _state.update {it.copy(
                            validationNameError = result.nameError,
                            validationLoginError = result.loginError,
                            validationPassError = result.passError
                        )
                        }
                    }
                }
            }
            is PasswordListEvent.SelectPassword -> {
                _state.update { it.copy(
                    selectedPassword = event.password,
                    isSelectedPasswordSheepOpen = true,
                ) }
            }
            else -> Unit
        }
    }
}
private val emptyPassword = Password(
    id = null,
    type = Type.ELSE,
    name = "",
    login = "",
    pass = "",
    photoBytes = null
)

