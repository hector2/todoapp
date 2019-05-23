package doritos.com.todoapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import doritos.com.todoapp.data.database.DatabaseTask

/**
 * The Data Access Object for the TaskModel class.
 */
@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY name")
    suspend fun getTasks(): List<DatabaseTask>

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTask(taskId: String): DatabaseTask

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(databaseTasks: List<DatabaseTask>)
}