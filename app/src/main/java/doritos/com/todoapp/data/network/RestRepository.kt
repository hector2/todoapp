package doritos.com.todoapp.data.network

import javax.inject.Inject
class RestRepository @Inject constructor(private val restDao: RestDao) {

    suspend fun getTasks() = restDao.getTasks()
}