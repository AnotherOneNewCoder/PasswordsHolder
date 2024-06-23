package ru.zhogin.passwordsholder.passwords.data

import kotlinx.coroutines.flow.Flow
import ru.zhogin.passwordsholder.database.PasswordDatabase
import ru.zhogin.passwordsholder.passwords.domain.Entrance
import ru.zhogin.passwordsholder.passwords.domain.EntranceDataSource

class SqlDelightEntranceDataSource(
    db: PasswordDatabase
) : EntranceDataSource {
    override fun getEntrance(): Flow<List<Entrance>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertEntrance(entrance: Entrance) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEntrance(id: Long) {
        TODO("Not yet implemented")
    }
}