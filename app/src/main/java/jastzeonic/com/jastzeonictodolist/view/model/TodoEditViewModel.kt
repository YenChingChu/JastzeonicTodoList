package jastzeonic.com.jastzeonictodolist.view.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.databinding.ObservableField
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import jastzeonic.com.jastzeonictodolist.RepositoryProvider
import jastzeonic.com.jastzeonictodolist.model.TodoModel
import jastzeonic.com.jastzeonictodolist.model.TodoRepository

class TodoEditViewModel(application: Application) : AndroidViewModel(application) {

    val onDeleteEvent = MutableLiveData<Boolean>()
    val onInsertEvent = MutableLiveData<Boolean>()


    private val repo: TodoRepository = RepositoryProvider.getDatabaseRepository(TodoRepository::class.java)
    val content = ObservableField<String>()
    val description = ObservableField<String>()
    var todoId = -1L


    var compositeDisposable = CompositeDisposable()

    fun getTodoModel(id: Long) {

        repo.getItemById(id).subscribe({ todoModel ->

            if (todoModel != null) {
                content.set(todoModel.todoContent)
                description.set(todoModel.todoDescription)
            }

        }, {}).addTo(compositeDisposable)

    }


    fun addTodoModel() {

        val model = TodoModel()
        if (content.get() != null && description.get() != null) {
            model.todoContent = content.get()!!
            model.todoDescription = description.get()!!
            if (todoId != -1L) {
                model.id = todoId
            }
        } else {
            return
        }


        repo.insert(model).subscribe({ onSuccess -> onInsertEvent.value = onSuccess > 0 }, { onError -> }).addTo(compositeDisposable)
    }

    fun deleteTodoModel() {
        if (todoId == -1L) {
            return
        }

        val model = TodoModel()
        model.id = todoId

        repo.delete(model).subscribe({ onSuccess -> onDeleteEvent.value = onSuccess > 0 }, {})
                .addTo(compositeDisposable)

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


}