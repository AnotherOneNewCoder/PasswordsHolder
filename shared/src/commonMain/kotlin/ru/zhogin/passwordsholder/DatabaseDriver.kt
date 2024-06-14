package ru.zhogin.passwordsholder

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriver {
    fun createDriver(): SqlDriver
}

const val DB_NAME = "password.db"