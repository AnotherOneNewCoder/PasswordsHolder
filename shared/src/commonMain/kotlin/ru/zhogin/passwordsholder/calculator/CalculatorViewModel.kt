package ru.zhogin.passwordsholder.calculator

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.zhogin.passwordsholder.passwords.domain.Entrance
import ru.zhogin.passwordsholder.passwords.domain.PasswordDataSource

class CalculatorViewModel(
    private val passwordDataSource: PasswordDataSource,
) : ViewModel() {


    private val _state = MutableStateFlow(CalculatorState())

    val state = combine(
        _state,
        passwordDataSource.getEntrance()
    ) { state, pass ->
        state.copy(
            secret = pass.firstOrNull()?.pass
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, CalculatorState())

    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Delete -> delete()
            is CalculatorAction.Clear -> {
                if (state.value.secret == null  && _state.value.number1.length >= 5) {
                    viewModelScope.launch {
                        passwordDataSource.insertEntrance(
                            Entrance(
                                id = 1,
                                pass = _state.value.number1
                            )
                        )
                        _state.update {
                            _state.value.copy(
                                number1 = "",
                                secret = passwordDataSource.getEntrance().firstOrNull().toString(),
                                number2 = "",
                                operation = null
                            )
                        }
                    }

                } else {
                    _state.update {
                        _state.value.copy(
                            number1 = "",
                            number2 = "",
                            operation = null,
                        )
                    }
                }
            }

            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Calculate -> calculate()
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if (_state.value.number1.isNotBlank()) {
            _state.update { _state.value.copy(operation = operation) }
        }
    }

    private fun calculate() {
        val number1 = _state.value.number1.toDoubleOrNull()
        val number2 = _state.value.number2.toDoubleOrNull()
        if (number1 != null && number2 != null) {
            val result = when (_state.value.operation) {
                is CalculatorOperation.Add -> number1 + number2
                is CalculatorOperation.Subtract -> number1 - number2
                is CalculatorOperation.Multiply -> number1 * number2
                is CalculatorOperation.Divide -> number1 / number2
                null -> return
            }
            _state.update {
                _state.value.copy(
                    number1 = result.toString().take(15),
                    number2 = "",
                    operation = null
                )
            }
        }
    }

    private fun delete() {
        when {
            _state.value.number1 == state.value.secret -> {
                viewModelScope.launch {
                    passwordDataSource.deleteEntrance(1)
                    _state.update {
                        _state.value.copy(
                            number1 = "",
                            secret = null,
                            operation = null,
                        )
                    }
                }
            }
            _state.value.number2.isNotBlank() -> _state.update {
                _state.value.copy(
                    number2 = _state.value.number2.dropLast(1)
                )
            }

            _state.value.operation != null -> _state.update {
                _state.value.copy(
                    operation = null
                )
            }

            _state.value.number1.isNotBlank() -> _state.update {
                _state.value.copy(
                    number1 = _state.value.number1.dropLast(1)
                )
            }
        }
    }

    private fun enterDecimal() {
        if (_state.value.operation == null && !_state.value.number1.contains(".") && _state.value.number1.isNotBlank()) {
            _state.update {
                _state.value.copy(
                    number1 = _state.value.number1 + "."
                )
            }
            return
        } else if (!_state.value.number2.contains(".") && _state.value.number2.isNotBlank()) {
            _state.update {
                _state.value.copy(
                    number2 = _state.value.number2 + "."
                )
            }
        }
    }

    private fun enterNumber(number: Int) {


        if (_state.value.operation == null) {
            if (_state.value.number1.length >= MAX_NUM_LENGTH) {
                return
            }
            _state.update {
                _state.value.copy(
                    number1 = _state.value.number1 + number
                )
            }
            return
        }
        if (_state.value.number2.length >= MAX_NUM_LENGTH) {
            return
        }
        _state.update {
            _state.value.copy(
                number2 = _state.value.number2 + number
            )
        }
    }


    companion object {
        private const val MAX_NUM_LENGTH = 8
    }
}