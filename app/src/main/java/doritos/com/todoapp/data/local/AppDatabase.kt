package doritos.com.todoapp.data.local

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import doritos.com.todoapp.data.Task
import doritos.com.todoapp.data.local.dao.TaskDao
import doritos.com.todoapp.utils.DATABASE_NAME
import doritos.com.todoapp.workers.SeedDatabaseWorker

/**
 * The Room database for this app
 */
@Database(entities = [Task::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}