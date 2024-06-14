package ru.zhogin.passwordsholder.di


import ru.zhogin.passwordsholder.DatabaseDriver
import ru.zhogin.passwordsholder.database.PasswordDatabase
import ru.zhogin.passwordsholder.passwords.data.SqlDelightPasswordDataSource
import ru.zhogin.passwordsholder.passwords.domain.PasswordDataSource

actual class AppModule(
) {
    actual val passwordDataSource: PasswordDataSource by lazy {
        SqlDelightPasswordDataSource(
            db = PasswordDatabase(
                driver = DatabaseDriver().createDriver()
            )
        )
    }
}