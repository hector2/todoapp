package doritos.com.todoapp.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import doritos.com.todoapp.data.local.AppDatabase
import doritos.com.todoapp.data.Task
import doritos.com.todoapp.utils.TASK_DATA_FILENAME


class SeedDatabaseWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    private val TAG by lazy { SeedDatabaseWorker::class.java.simpleName }

    override fun doWork(): Result {
        val taskType = object : TypeToken<List<Task>>() {}.type
        var jsonReader: JsonReader? = null

        return try {
            val inputStream = applicationContext.assets.open(TASK_DATA_FILENAME)
            jsonReader = JsonReader(inputStream.reader())
            val taskList: List<Task> = Gson().fromJson(jsonReader, taskType)
            val database = AppDatabase.getInstance(applicationContext)
            database.taskDao().insertAll(taskList)
            Result.success()
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        } finally {
            jsonReader?.close()
        }
    }
}