package doritos.com.todoapp.data.remote

class RestRepository private constructor(private val restDao: RestDao) {

    fun getTasks() = restDao.getTasks()

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: RestRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance
                    ?: RestRepository(RestDao.create()).also { instance = it }
            }
    }
}