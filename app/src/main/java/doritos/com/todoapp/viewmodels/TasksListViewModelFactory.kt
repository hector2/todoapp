package doritos.com.todoapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import doritos.com.todoapp.data.AppRepository


class TasksListViewModelFactory(
    private val repository: AppRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = TaskListViewModel(repository) as T
}