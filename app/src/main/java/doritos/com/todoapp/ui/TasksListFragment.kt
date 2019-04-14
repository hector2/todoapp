package doritos.com.todoapp.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import doritos.com.todoapp.adapters.TaskAdapter
import doritos.com.todoapp.databinding.TasksListFragmentBinding
import doritos.com.todoapp.utils.InjectorUtils
import doritos.com.todoapp.viewmodels.TaskListViewModel

class TasksListFragment : Fragment() {

    companion object {
        fun newInstance() = TasksListFragment()
    }

    private lateinit var viewModel: TaskListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = TasksListFragmentBinding.inflate(inflater, container, false)

        val context = context ?: return binding.root
        val factory = InjectorUtils.provideTaskListViewModelFactory(context)
        viewModel = ViewModelProviders.of(this, factory).get(TaskListViewModel::class.java)

        val adapter = TaskAdapter()
        binding.taskList.adapter = adapter
        subscribeUi(adapter)

        return binding.root
    }


    private fun subscribeUi(adapter: TaskAdapter) {

        //aqui observa lo q llega del viewmodel
       viewModel.getTasks().observe(viewLifecycleOwner, Observer { tasks ->
            if (tasks != null) adapter.submitList(tasks)
        })
    }



}
