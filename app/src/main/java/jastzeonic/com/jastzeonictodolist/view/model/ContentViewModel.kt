package jastzeonic.com.jastzeonictodolist.view.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import jastzeonic.com.jastzeonictodolist.RepositoryProvider
import jastzeonic.com.jastzeonictodolist.model.TodoModel
import jastzeonic.com.jastzeonictodolist.model.TodoRepository

class ContentViewModel(application: Application) : AndroidViewModel(application) {

    val onDeleteEvent = MutableLiveData<Boolean>()
    val onInsertEvent = MutableLiveData<Boolean>()


    var repo: TodoRepository = RepositoryProvider.getDatabaseRepository(TodoRepository::class.java)
    val content = ObservableField<String>()
    val description = ObservableField<String>()
    var todoId = -1L

    fun getTodoModel(id: Long) {

        repo.getItemById(id).subscribe({ todoModels ->

            if (todoModels.isNotEmpty()) {
                val todoModel = todoModels.first()
                content.set(todoModel.todoTitle)
                description.set(todoModel.todoContent)
            }

        }, {})

    }


    fun addTodoModel() {

        val model = TodoModel()
        if (content.get() != null && description.get() != null) {
            model.todoTitle = content.get()!!
            model.todoContent = description.get()!!
        } else {
            return
        }


        repo.insert(model).subscribe({ onSuccess -> onInsertEvent.value = onSuccess > 0 }, {})
    }

    fun deleteTodoModel() {
        if (todoId == -1L) {
            return
        }

        val model = TodoModel()
        model.id = todoId

        repo.delete(model).subscribe({ onSuccess -> onDeleteEvent.value = onSuccess > 0 }, {})

    }


}