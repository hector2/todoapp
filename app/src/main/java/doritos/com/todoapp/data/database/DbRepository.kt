package doritos.com.todoapp.data.database

import doritos.com.todoapp.data.database.dao.TaskDao
import javax.inject.Inject

class DbRepository @Inject constructor(private val taskDao: TaskDao) {

    suspend fun getTasks() = taskDao.getTasks()

    suspend fun getTask(taskId: String) = taskDao.getTask(taskId)
    suspend fun insertAll(list: List<DatabaseTask>) = taskDao.insertAll(list)
}