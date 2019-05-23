package doritos.com.todoapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import doritos.com.todoapp.data.database.DatabaseTask
import doritos.com.todoapp.data.database.DbRepository
import doritos.com.todoapp.data.network.RestRepository
import doritos.com.todoapp.data.network.TaskDTO
import doritos.com.todoapp.domain.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class AppRepository @Inject constructor(
    private val restRepository: RestRepository,
    private val dbRepository: DbRepository
) {



    suspend fun refreshTasks() {
        withContext(Dispatchers.IO) {
            val tasklist = restRepository.getTasks().await()
            dbRepository.insertAll(tasklist.asDatabaseModel())
        }
    }


     suspend fun getTasks(): LiveData<List<Task>> {
        val observableFromApi = getTasksFromApi()
//
//         val result =  Transformations.map(observableFromApi) {
//             it.map {x ->
//                 Task(taskId = x.taskId,name = x.name)
//             }
//         }
//
//         return result

        val observableFromDb = getTasksFromDb()
         return Transformations.map(observableFromDb) { x ->
             x.map {
                 Task(taskId = it.taskId,name = it.name)
             }
         }


        //return observableFromApi

        /*return CombinedLiveData<List<DatabaseTask>, List<DatabaseTask>, List<DatabaseTask>>(observableFromApi, observableFromDb) { a, b ->
            val set = LinkedHashSet<DatabaseTask>()
            a?.let { set.addAll(it) }
            b?.let { set.addAll(it) }
            ArrayList(set)
        }*/
    }

    suspend fun getTask(taskId: String): DatabaseTask = dbRepository.getTask(taskId)


    private fun getTasksFromDb(): LiveData<List<DatabaseTask>> {
        return dbRepository.getTasks()
    }

    private suspend fun getTasksFromApi(): LiveData<List<TaskDTO>> {
        val api = restRepository.getTasks()
        val liveData = MutableLiveData<List<TaskDTO>>()
        val list = api.await()

        liveData.value = list

        //dbRepository.insertAll(list.asDatabaseModel())
        return liveData
    }

}


