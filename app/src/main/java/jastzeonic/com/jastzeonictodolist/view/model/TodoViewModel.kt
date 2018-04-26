package jastzeonic.com.jastzeonictodolist.view.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import android.util.Log
import jastzeonic.com.jastzeonictodolist.RepositoryProvider
import jastzeonic.com.jastzeonictodolist.model.TodoModel
import jastzeonic.com.jastzeonictodolist.model.TodoRepository


class TodoViewModel(application: Application) : AndroidViewModel(application) {

    val onDeleteEvent = MutableLiveData<Boolean>()
    val onInsertEvent = MutableLiveData<Boolean>()


    val todoListObservable = ObservableField<List<TodoModel>>()


    private val repo: TodoRepository = RepositoryProvider.getDatabaseRepository(TodoRepository::class.java)
    val content = ObservableField<String>()
    val description = ObservableField<String>()
    var todoId = -1L

    fun getTodoModel(id: Long) {

        repo.getItemById(id).subscribe({ todoModel ->

            if (todoModel != null) {
                content.set(todoModel.todoContent)
                description.set(todoModel.todoDescription)
            }

        }, {})

    }


    fun addTodoModel() {

        val model = TodoModel()
        if (content.get() != null && description.get() != null) {
            model.todoContent = content.get()!!
            model.todoDescription = description.get()!!
        } else {
            return
        }


        repo.insert(model).subscribe({ onSuccess -> onInsertEvent.value = onSuccess > 0 }, { onError -> })
    }


    fun getTodoList() {
        repo.getAll().subscribe({ todoList -> todoListObservable.set(todoList) }, { onError -> Log.d("####", "onError:" + onError.message.toString()) })
    }


    fun deleteTodoModel(id: Long) {
        val model = TodoModel()
        model.id = id

        repo.delete(model).subscribe({ onSuccess -> onDeleteEvent.value = onSuccess > 0 }, {})

    }



}