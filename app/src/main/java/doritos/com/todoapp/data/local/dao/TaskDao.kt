package doritos.com.todoapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import doritos.com.todoapp.data.Task

/**
 * The Data Access Object for the TaskModel class.
 */
@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY name")
    fun getTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    fun getTask(taskId: String): LiveData<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tasks: List<Task>)
}