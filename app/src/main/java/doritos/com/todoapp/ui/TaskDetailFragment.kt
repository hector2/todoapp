package doritos.com.todoapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import doritos.com.todoapp.dagger.injector
import doritos.com.todoapp.databinding.TaskDetailFragmentBinding
import doritos.com.todoapp.viewmodels.TaskDetailViewModel
import doritos.com.todoapp.viewmodels.ViewModelFactory
import javax.inject.Inject


class TaskDetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<TaskDetailViewModel>

    private lateinit var viewModel: TaskDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val factory = InjectorUtils.provideTaskDetailViewModelFactory(requireActivity(), taskId)
        //val taskDetailViewModel = ViewModelProviders.of(this, factory)
        //    .get(TaskDetailViewModel::class.java)

        //return inflater.inflate(R.layout.task_detail_fragment, container, false)

        val binding = TaskDetailFragmentBinding.inflate(inflater, container, false)
        binding.id = TaskDetailFragmentArgs.fromBundle(this.arguments!!).taskId


        val context = context ?: return binding.root
        injector.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TaskDetailViewModel::class.java)
        return binding.root
    }


}
