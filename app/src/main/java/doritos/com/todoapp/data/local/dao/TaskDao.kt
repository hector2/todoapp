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
    suspend fun getTasks(): List<Task>

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTask(taskId: String): Task

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tasks: List<Task>)
}