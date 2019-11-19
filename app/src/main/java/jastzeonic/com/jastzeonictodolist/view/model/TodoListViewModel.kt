package jastzeonic.com.jastzeonictodolist.view.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.databinding.ObservableField
import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import jastzeonic.com.jastzeonictodolist.RepositoryProvider
import jastzeonic.com.jastzeonictodolist.model.TodoModel
import jastzeonic.com.jastzeonictodolist.model.TodoRepository

class TodoListViewModel(application: Application) : AndroidViewModel(application) {


    var repo: TodoRepository = RepositoryProvider.getDatabaseRepository(TodoRepository::class.java)


    val todoListObservable = ObservableField<List<TodoModel>>()

    var compositeDisposable = CompositeDisposable()


    private val onDeleteSuccessEvent = MutableLiveData<Boolean>()


    fun getTodoList() {
        repo.getAll().subscribe({ todoList -> todoListObservable.set(todoList) }, { onError -> Log.d("####", "onError:" + onError.message.toString()) })
                .addTo(compositeDisposable)
    }


    fun deleteTodoModel(id: Long) {
        val model = TodoModel()
        model.id = id

        repo.delete(model).subscribe({ onSuccess -> onDeleteSuccessEvent.value = onSuccess > 0 }, {})
                .addTo(compositeDisposable)

    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}