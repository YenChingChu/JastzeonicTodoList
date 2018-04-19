package jastzeonic.com.jastzeonictodolist.view.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import jastzeonic.com.jastzeonictodolist.RepositoryProvider
import jastzeonic.com.jastzeonictodolist.model.TodoModel
import jastzeonic.com.jastzeonictodolist.model.TodoRepository

class TodoListViewModel(application: Application) : AndroidViewModel(application) {


    var repo: TodoRepository = RepositoryProvider.getDatabaseRepository(TodoRepository::class.java)


    val todoListObservable = ObservableField<List<TodoModel>>()


    private val onDeleteSuccessEvent = MutableLiveData<Boolean>()


    fun getTodoList() {
        repo.getAll().subscribe({ todoList -> todoListObservable.set(todoList) }, {})
    }


    fun deleteTodoModel(id: Long) {
        val model = TodoModel()
        model.id = id

        repo.delete(model).subscribe({ onSuccess -> onDeleteSuccessEvent.value = onSuccess > 0 }, {})

    }


}