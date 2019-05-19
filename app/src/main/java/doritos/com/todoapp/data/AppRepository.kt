package doritos.com.todoapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import doritos.com.todoapp.data.local.DbRepository
import doritos.com.todoapp.data.remote.RestRepository
import java.util.LinkedHashSet
import javax.inject.Inject
import kotlin.collections.ArrayList


class AppRepository @Inject constructor(
    private val restRepository: RestRepository,
    private val dbRepository: DbRepository
) {
    suspend fun getTasks(): LiveData<List<Task>> {
        val observableFromApi = getTasksFromApi()
        val observableFromDb = getTasksFromDb()

        return CombinedLiveData<List<Task>, List<Task>, List<Task>>(observableFromApi, observableFromDb) { a, b ->
            val set = LinkedHashSet<Task>()
            a?.let { set.addAll(it) }
            b?.let { set.addAll(it) }
            ArrayList(set)
        }
    }

    suspend fun getTask(taskId: String): Task = dbRepository.getTask(taskId)


    private suspend fun getTasksFromDb(): LiveData<List<Task>> {
        val liveData = MutableLiveData<List<Task>>()
        val db = dbRepository.getTasks()
        liveData.value = db
        return liveData
    }

    private suspend fun getTasksFromApi(): LiveData<List<Task>> {
        val api = restRepository.getTasks()
        val liveData = MutableLiveData<List<Task>>()
        val list = api.await()
        liveData.value = list
        dbRepository.insertAll(list)
        return liveData
    }

}