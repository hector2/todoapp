package doritos.com.todoapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import doritos.com.todoapp.data.local.DbRepository
import doritos.com.todoapp.data.remote.RestRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.LinkedHashSet
import javax.inject.Inject
import kotlin.collections.ArrayList

class AppRepository @Inject constructor(
    private val restRepository: RestRepository,
    private val dbRepository: DbRepository
) {
    fun getTasks(): LiveData<List<Task>> {
        val observableFromApi = getTasksFromApi()
        val observableFromDb = getTasksFromDb()

        val merged = CombinedLiveData<List<Task>, List<Task>, List<Task>>(observableFromApi, observableFromDb) { a, b ->
            val set = LinkedHashSet<Task>()
            a?.let { set.addAll(it) }
            b?.let { set.addAll(it) }
            ArrayList(set)
        }
        return merged
    }

    fun getTask(taskId: String): LiveData<Task> = dbRepository.getTask(taskId)

    fun getTasksFromDb(): LiveData<List<Task>> {
        val db = dbRepository.getTasks()
        return db
    }

    fun getTasksFromApi(): LiveData<List<Task>> {


        val api = restRepository.getTasks()
        val livedata = MutableLiveData<List<Task>>()
        api.enqueue(object : Callback<List<Task>> {
            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                livedata.value = null
            }

            override fun onResponse(call: Call<List<Task>>, response: Response<List<Task>>) {


                if (response.body() != null) {
                    GlobalScope.launch {
                        // launch new coroutine in background and continue
                        dbRepository.insertAll(response.body()!!)
                    }
                }

                livedata.value = response.body()


            }
        })
        return livedata


/*
        return apiInterface.getCryptocurrencies("0")
            .doOnNext {
                Log.e("REPOSITORY API * ", it.size.toString())
                for (item in it) {
                    cryptocurrenciesDao.insertCryptocurrency(item)
                }
            }
        */

    }

}