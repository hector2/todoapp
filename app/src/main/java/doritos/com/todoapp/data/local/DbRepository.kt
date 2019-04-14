package doritos.com.todoapp.data.local

import doritos.com.todoapp.data.local.dao.TaskDao

class DbRepository private constructor(private val taskDao: TaskDao) {

    fun getTasks() = taskDao.getTasks()

    fun getTask(taskId: String) = taskDao.getTask(taskId)


    companion object {

        // For Singleton instantiation
        @Volatile private var instance: DbRepository? = null

        fun getInstance(taskDao: TaskDao) =
            instance ?: synchronized(this) {
                instance
                    ?: DbRepository(taskDao).also { instance = it }
            }
    }
}