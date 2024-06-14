package ru.zhogin.passwordsholder.passwords.domain

object PasswordValidator {

    fun validatePassword(password: Password) : ValidationResult {
        var result = ValidationResult()

        if (password.name.isBlank()) {
            result = result.copy(nameError = "The title can't be empty")
        }
        if (password.login.isBlank()) {
            result = result.copy(nameError = "The login can't be empty")
        }
        if (password.pass.isBlank()) {
            result = result.copy(nameError = "The password can't be empty")
        }

        return result
    }

    data class ValidationResult(
        val nameError: String? = null,
        val loginError: String? = null,
        val passError: String? = null,
    )

}