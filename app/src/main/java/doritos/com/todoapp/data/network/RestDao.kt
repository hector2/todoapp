package doritos.com.todoapp.data.network


import doritos.com.todoapp.data.database.DatabaseTask
import kotlinx.coroutines.Deferred
import retrofit2.http.GET


public interface RestDao {
    @GET("tasks")
     fun getTasks(
    ): Deferred<List<TaskDTO>>

}