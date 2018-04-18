package jastzeonic.com.jastzeonictodolist.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import jastzeonic.com.jastzeonictodolist.model.TodoModel


@Database(entities = [(TodoModel::class)], version = 1)
abstract class TodoDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "Expenses"
    }

    abstract fun getTodoDao(): TodoDao

}