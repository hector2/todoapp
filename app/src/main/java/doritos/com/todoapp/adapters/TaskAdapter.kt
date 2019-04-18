package doritos.com.todoapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import doritos.com.todoapp.data.Task
import doritos.com.todoapp.databinding.TaskItemBinding
import doritos.com.todoapp.ui.TasksListFragmentDirections

class TaskAdapter : ListAdapter<Task, TaskAdapter.ViewHolder>(TaskDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = getItem(position)
        holder.apply {
            bind(createOnClickListener(task.taskId), task)
            itemView.tag = task
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TaskItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    private fun createOnClickListener(taskId: String): View.OnClickListener {
        return View.OnClickListener {
           val direction = TasksListFragmentDirections.actionTasksListFragmentToTaskDetail(taskId)
           it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(
        private val binding: TaskItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Task) {
            binding.apply {
                clickListener = listener
                task = item
                executePendingBindings()
            }
        }
    }
}

private class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {

    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.taskId == newItem.taskId
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}