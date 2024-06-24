package ru.zhogin.passwordsholder.passwords.domain

import kotlinx.coroutines.flow.Flow

interface PasswordDataSource {
    fun getPasswords(): Flow<List<Password>>
    suspend fun insertPassword(password: Password)
    suspend fun deletePassword(id: Long)

    fun getEntrance(): Flow<List<Entrance>>
    suspend fun insertEntrance(entrance: Entrance)
    suspend fun deleteEntrance(id: Long)
}