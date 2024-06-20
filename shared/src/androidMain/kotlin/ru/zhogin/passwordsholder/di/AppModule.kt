package ru.zhogin.passwordsholder.di

import android.content.Context
import ru.zhogin.passwordsholder.DatabaseDriver
import ru.zhogin.passwordsholder.core.data.ImageStorage
import ru.zhogin.passwordsholder.database.PasswordDatabase
import ru.zhogin.passwordsholder.passwords.data.SqlDelightPasswordDataSource
import ru.zhogin.passwordsholder.passwords.domain.PasswordDataSource

actual class AppModule(
    private val context: Context,
) {
    actual val passwordDataSource: PasswordDataSource by lazy {
        SqlDelightPasswordDataSource(
            db = PasswordDatabase(
                driver = DatabaseDriver(context).createDriver(),
            ),
            imageStorage = ImageStorage(context)
        )
    }
}