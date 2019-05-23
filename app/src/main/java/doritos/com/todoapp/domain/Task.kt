package doritos.com.todoapp.domain


data class Task(
    val taskId: String,
    val name: String
) {
    override fun toString() = "$taskId,$name"
}
