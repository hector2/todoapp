package doritos.com.todoapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import doritos.com.todoapp.data.database.DatabaseTask
import doritos.com.todoapp.data.database.DbRepository
import doritos.com.todoapp.data.network.RestRepository
import doritos.com.todoapp.data.network.TaskDTO
import doritos.com.todoapp.domain.Task
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

        return observableFromApi

        /*return CombinedLiveData<List<DatabaseTask>, List<DatabaseTask>, List<DatabaseTask>>(observableFromApi, observableFromDb) { a, b ->
            val set = LinkedHashSet<DatabaseTask>()
            a?.let { set.addAll(it) }
            b?.let { set.addAll(it) }
            ArrayList(set)
        }*/
    }

    suspend fun getTask(taskId: String): DatabaseTask = dbRepository.getTask(taskId)


    private suspend fun getTasksFromDb(): LiveData<List<DatabaseTask>> {
        val liveData = MutableLiveData<List<DatabaseTask>>()
        val db = dbRepository.getTasks()
        liveData.value = db
        return liveData
    }

    private suspend fun getTasksFromApi(): LiveData<List<Task>> {
        val api = restRepository.getTasks()
        val liveData = MutableLiveData<List<Task>>()
        val list = api.await().asDomainModel()

        liveData.value = list

        //dbRepository.insertAll(list.asDatabaseModel())
        return liveData
    }

}


