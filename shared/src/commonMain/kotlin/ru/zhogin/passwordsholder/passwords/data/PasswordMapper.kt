package ru.zhogin.passwordsholder.passwords.data

import ru.zhogin.passwordsholder.core.data.ImageStorage
import ru.zhogin.passwordsholder.passwords.domain.Password
import ru.zhogin.passwordsholder.passwords.domain.Type
import ruzhogin.PasswordEntity

suspend fun PasswordEntity.toPassword(imageStorage: ImageStorage) : Password {
    return Password(
        id = id,
        type = Type.valueOf(type),
        name = name,
        login = login,
        pass = pass,
        photoBytes = imagePath?.let { imageStorage.getImage(it) }
    )
}