package ru.zhogin.passwordsholder.passwords.domain

import kotlinx.coroutines.flow.Flow

interface EntranceDataSource {
    fun getEntrance(): Flow<List<Entrance>>
    suspend fun insertEntrance(entrance: Entrance)
    suspend fun deleteEntrance(id: Long)
}