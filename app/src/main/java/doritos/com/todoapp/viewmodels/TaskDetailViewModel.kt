package doritos.com.todoapp.viewmodels

import androidx.lifecycle.ViewModel
import doritos.com.todoapp.data.AppRepository

class TaskDetailViewModel(
    repository: AppRepository,
    private val taskId: String
) : ViewModel() {

    //val task: LiveData<Task>

    override fun onCleared() {
        super.onCleared()
    }

    init {
        //task = repository.getTask(taskId)
    }
}