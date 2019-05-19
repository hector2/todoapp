package doritos.com.todoapp.data.remote


import doritos.com.todoapp.data.Task
import kotlinx.coroutines.Deferred
import retrofit2.http.GET


public interface RestDao {
    @GET("tasks")
     fun getTasks(
    ): Deferred<List<Task>>

}