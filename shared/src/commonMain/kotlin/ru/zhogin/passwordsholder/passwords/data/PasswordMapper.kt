package ru.zhogin.passwordsholder.passwords.data

import ru.zhogin.passwordsholder.passwords.domain.Password
import ru.zhogin.passwordsholder.passwords.domain.Type
import ruzhogin.PasswordEntity

fun PasswordEntity.toPassword() : Password {
    return Password(
        id = id,
        type = Type.valueOf(type),
        name = name,
        login = login,
        pass = pass,
        photoBytes = null
    )
}