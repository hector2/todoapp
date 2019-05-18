package doritos.com.todoapp.data.remote

import javax.inject.Inject
class RestRepository @Inject constructor(private val restDao: RestDao) {

    fun getTasks() = restDao.getTasks()
}