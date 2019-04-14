package doritos.com.todoapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import doritos.com.todoapp.data.AppRepository
import doritos.com.todoapp.data.local.DbRepository

class TaskDetailViewModelFactory(
    private val repository: AppRepository,
    private val taskId: String
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskDetailViewModel(repository, taskId) as T
    }
}