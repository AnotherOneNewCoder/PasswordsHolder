package ru.zhogin.passwordsholder.passwords.domain

data class Password(
    val id: Long?,
    val type: Type,
    val name: String,
    val login: String,
    val pass: String,
    val photoBytes: ByteArray?
)

enum class Type {
    EMAIL, WEB, APPS, ELSE
}
