package doritos.com.todoapp.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import doritos.com.todoapp.data.AppRepository
import doritos.com.todoapp.data.database.DatabaseTask
import doritos.com.todoapp.domain.Task
import kotlinx.coroutines.launch
import javax.inject.Inject

class TaskListViewModel @Inject constructor(
    private val appRepository:AppRepository
) : ViewModel() {


    //se usa mediador como intermediario entre lo que viene de Room y lo que se manda a la view
    //RECORDAR: livedata es un observable que SABE DE LOS CICLOS DE VIDA. de esta forma no te preocupas de los ciclos de vida de la app
    private val taskList = MediatorLiveData<List<Task>>()

    init {

        viewModelScope.launch {

            appRepository.refreshTasks()
            //aqui viene lo de room, que es un livedata
            val liveTaskList = appRepository.getTasks()

            //aqui se podrian hacer modificaciones y añadirlo al mediator. setvalue funciona de modo que si hay observers, le manda el valor a ellos.
            taskList.addSource(liveTaskList, taskList::setValue)
        }


    }

    fun getTasks() = taskList

}