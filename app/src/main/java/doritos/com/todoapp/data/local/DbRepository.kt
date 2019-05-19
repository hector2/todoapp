package doritos.com.todoapp.data.local

import doritos.com.todoapp.data.Task
import doritos.com.todoapp.data.local.dao.TaskDao
import javax.inject.Inject

class DbRepository @Inject constructor(private val taskDao: TaskDao) {

    suspend fun getTasks() = taskDao.getTasks()

    suspend fun getTask(taskId: String) = taskDao.getTask(taskId)
    suspend fun insertAll(list: List<Task>) = taskDao.insertAll(list)
}