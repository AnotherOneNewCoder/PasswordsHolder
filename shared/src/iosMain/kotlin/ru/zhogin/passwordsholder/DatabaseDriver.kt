package ru.zhogin.passwordsholder

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import ru.zhogin.passwordsholder.database.PasswordDatabase

actual class DatabaseDriver {
    actual fun createDriver(): SqlDriver = NativeSqliteDriver(
        schema = PasswordDatabase.Schema,
        name = DB_NAME,
    )
}

/*Inside your project.pbxproj add -lsqlite3 otherwise you’ll get undenfied symbols error


OTHER_LDFLAGS = (
"$(inherited)",
"-framework",
composeApp,
"-lsqlite3",
);
*/