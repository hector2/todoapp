package doritos.com.todoapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class DatabaseTask(
    @PrimaryKey @ColumnInfo(name = "id")
    val taskId: String,
    val name: String
) {

    override fun toString() = "$taskId,$name"
}