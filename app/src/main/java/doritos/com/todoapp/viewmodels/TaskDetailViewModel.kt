package doritos.com.todoapp.viewmodels

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class TaskDetailViewModel @Inject constructor(
) : ViewModel() {

    //val task: LiveData<DatabaseTask>

    override fun onCleared() {
        super.onCleared()
    }

    init {
        //task = repository.getTask(taskId)
    }
}