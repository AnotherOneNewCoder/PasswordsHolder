package ru.zhogin.passwordsholder.passwords.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope
import kotlinx.datetime.Clock
import ru.zhogin.passwordsholder.core.data.ImageStorage
import ru.zhogin.passwordsholder.database.PasswordDatabase
import ru.zhogin.passwordsholder.passwords.domain.Password
import ru.zhogin.passwordsholder.passwords.domain.PasswordDataSource

class SqlDelightPasswordDataSource(
   db: PasswordDatabase,
   private val imageStorage: ImageStorage
) : PasswordDataSource {

   private val queries = db.passwordQueries
   private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
   override fun getPasswords(): Flow<List<Password>> {
      return queries
         .getPasswords()
         .asFlow()
         .mapToList(ioDispatcher)
         .map { list ->
            supervisorScope {
               list.map {
                  async { it.toPassword(imageStorage) }
               }
                  .map { it.await() }
            }
         }
   }

   override suspend fun insertPassword(password: Password) {
      val imagePath = password.photoBytes?.let {
         imageStorage.saveImage(it)
      }
      queries.insertPasswordEntity(
         id = password.id,
         type = password.type.toString(),
         name = password.name,
         login = password.login,
         pass = password.pass,
         createdAt = Clock.System.now().toEpochMilliseconds(),
         imagePath = imagePath
      )
   }

   override suspend fun deletePassword(id: Long) {
      val entity = queries.selectPasswordEntity(id).executeAsOne()
      entity.imagePath?.let {
         imageStorage.deleteImage(it)
      }
      queries.deletePasswordEntity(id)
   }
}