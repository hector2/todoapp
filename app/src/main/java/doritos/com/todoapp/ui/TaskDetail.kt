package doritos.com.todoapp.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import doritos.com.todoapp.R
import doritos.com.todoapp.viewmodels.TaskDetailViewModel


class TaskDetail : Fragment() {


    private lateinit var viewModel: TaskDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val taskId = TaskDetailArgs.fromBundle(arguments!!).taskId

        //val factory = InjectorUtils.provideTaskDetailViewModelFactory(requireActivity(), taskId)
        //val taskDetailViewModel = ViewModelProviders.of(this, factory)
        //    .get(TaskDetailViewModel::class.java)

        return inflater.inflate(R.layout.task_detail_fragment, container, false)
    }



}
