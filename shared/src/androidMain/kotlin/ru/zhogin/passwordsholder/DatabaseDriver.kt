package ru.zhogin.passwordsholder

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import ru.zhogin.passwordsholder.database.PasswordDatabase

actual class DatabaseDriver(private val context: Context) {
    actual fun createDriver(): SqlDriver = AndroidSqliteDriver(
        schema = PasswordDatabase.Schema,
        context = context,
        name = DB_NAME,
    )
}