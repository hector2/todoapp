package doritos.com.todoapp.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import doritos.com.todoapp.data.database.AppDatabase
import doritos.com.todoapp.data.database.DatabaseTask
import doritos.com.todoapp.utils.TASK_DATA_FILENAME
import javax.inject.Inject


class SeedDatabaseWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    private val TAG by lazy { SeedDatabaseWorker::class.java.simpleName }

    @Inject
    lateinit var database: AppDatabase

    override fun doWork(): Result {
        val taskType = object : TypeToken<List<DatabaseTask>>() {}.type
        var jsonReader: JsonReader? = null

        return try {
            val inputStream = applicationContext.assets.open(TASK_DATA_FILENAME)
            jsonReader = JsonReader(inputStream.reader())
            val databaseTaskList: List<DatabaseTask> = Gson().fromJson(jsonReader, taskType)
            //val database = AppDatabase.getInstance(applicationContext)
            //database.taskDao().insertAll(taskList)
            Result.success()
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        } finally {
            jsonReader?.close()
        }
    }
}