package doritos.com.todoapp.data.network

import com.google.gson.annotations.SerializedName

data class TaskDTO(
    @SerializedName("ID")
    val taskId: String,
    @SerializedName("name")
    val name: String
) {
    override fun toString() = "$taskId,$name"
}

