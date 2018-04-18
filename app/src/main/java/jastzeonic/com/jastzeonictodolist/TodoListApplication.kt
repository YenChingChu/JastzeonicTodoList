package jastzeonic.com.jastzeonictodolist

import android.app.Application
import jastzeonic.com.jastzeonictodolist.model.TodoRepository

class TodoListApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RepositoryProvider.initDatabase(TodoRepository::class.java, this)
    }

}