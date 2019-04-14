package doritos.com.todoapp.utils

import android.content.Context
import doritos.com.todoapp.data.AppRepository
import doritos.com.todoapp.data.local.AppDatabase
import doritos.com.todoapp.data.local.DbRepository
import doritos.com.todoapp.data.remote.RestDao
import doritos.com.todoapp.data.remote.RestRepository
import doritos.com.todoapp.viewmodels.TaskDetailViewModelFactory
import doritos.com.todoapp.viewmodels.TasksListViewModelFactory


/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {

    private fun getDBRepository(context: Context): DbRepository {
        return DbRepository.getInstance(AppDatabase.getInstance(context).taskDao())
    }




    fun provideTaskListViewModelFactory(context: Context): TasksListViewModelFactory {
        val dbRepository = getDBRepository(context)


        val appRepository = AppRepository(RestRepository.getInstance(),dbRepository)
        return TasksListViewModelFactory(appRepository)
    }

    fun provideTaskDetailViewModelFactory(context: Context, taskId: String): TaskDetailViewModelFactory {

        val dbRepository = getDBRepository(context)
        val appRepository = AppRepository(RestRepository.getInstance(),dbRepository)

        return TaskDetailViewModelFactory(appRepository, taskId)
    }

}