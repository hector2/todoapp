package doritos.com.todoapp.data

import doritos.com.todoapp.data.database.DatabaseTask
import doritos.com.todoapp.data.network.TaskDTO
import doritos.com.todoapp.domain.Task

fun List<TaskDTO>.asDatabaseModel(): List<DatabaseTask> = map {
    DatabaseTask(taskId = it.taskId, name = it.name)
}

fun List<TaskDTO>.asDomainModel(): List<Task> = map {
    Task(taskId = it.taskId, name = it.name)
}