package doritos.com.todoapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import doritos.com.todoapp.data.database.dao.TaskDao

/**
 * The Room database for this app
 */
@Database(entities = [DatabaseTask::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}