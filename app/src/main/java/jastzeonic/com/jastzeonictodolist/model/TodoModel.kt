package jastzeonic.com.jastzeonictodolist.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = TodoModel.TABLE_NAME)
class TodoModel {

    companion object {
        const val TABLE_NAME = "todo_model"
    }

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    var todoTitle = ""

    var todoContent = ""

    var isFinished = false
}