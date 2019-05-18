package doritos.com.todoapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tasks")

data class Task(
    @PrimaryKey @ColumnInfo(name = "id")
    @SerializedName("ID")
    val taskId: String,
    @SerializedName("name")
    val name: String
) {

    override fun toString() = "$taskId,$name"
}