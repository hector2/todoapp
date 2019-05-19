package doritos.com.todoapp.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import doritos.com.todoapp.adapters.TaskAdapter
import doritos.com.todoapp.dagger.injector
import doritos.com.todoapp.databinding.TasksListFragmentBinding
import doritos.com.todoapp.viewmodels.TaskListViewModel
import doritos.com.todoapp.viewmodels.ViewModelFactory
import javax.inject.Inject

class TasksListFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<TaskListViewModel>

    private lateinit var viewModel: TaskListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val binding = TasksListFragmentBinding.inflate(inflater, container, false)

        val context = context ?: return binding.root
        injector.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TaskListViewModel::class.java)

        val adapter = TaskAdapter()
        binding.taskList.adapter = adapter
        subscribeUi(adapter)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    private fun subscribeUi(adapter: TaskAdapter) {

        //aqui observa lo q llega del viewmodel
        viewModel.getTasks().observe(viewLifecycleOwner, Observer { tasks ->

            if (tasks != null) adapter.submitList(tasks)
        })
    }


}
