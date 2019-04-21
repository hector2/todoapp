package doritos.com.todoapp.data.local

import doritos.com.todoapp.data.local.dao.TaskDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DbRepository @Inject constructor(private val taskDao: TaskDao) {

    fun getTasks() = taskDao.getTasks()

    fun getTask(taskId: String) = taskDao.getTask(taskId)
}