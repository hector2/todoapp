package doritos.com.todoapp.ui

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer


import doritos.com.todoapp.adapters.TaskAdapter
import doritos.com.todoapp.dagger.AppComponent
import doritos.com.todoapp.dagger.DaggerAppComponent
import doritos.com.todoapp.data.AppRepository
import doritos.com.todoapp.databinding.TasksListFragmentBinding
import doritos.com.todoapp.viewmodels.TaskListViewModel
import javax.inject.Inject

class TasksListFragment : Fragment() {



    //lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: TaskListViewModel

    @Inject
    lateinit var repo: AppRepository



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val binding = TasksListFragmentBinding.inflate(inflater, container, false)

        val context = context ?: return binding.root
        //val factory = InjectorUtils.provideTaskListViewModelFactory(context)
        //viewModel = ViewModelProviders.of(this).get(TaskListViewModel::class.java)

        //activity?.application?.let { DaggerAppComponent.builder().application(application = it).build().inject(this) }

        //DaggerAppComponent.create().inject(this)

        activity?.application?.let { DaggerAppComponent.builder().context(it).build().inject(this) }




        viewModel = TaskListViewModel(repo)


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
