package doritos.com.todoapp.data.remote


import doritos.com.todoapp.data.Task
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


public interface RestDao {
    @GET("todos")
    fun getTasks(
    ): Call<List<Task>>


    companion object {
        fun create(): RestDao {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(
                    GsonConverterFactory.create()
                ).build()

            return retrofit.create(RestDao::class.java)
        }
    }

}