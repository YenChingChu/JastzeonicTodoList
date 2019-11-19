package jastzeonic.com.jastzeonictodolist

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jastzeonic.com.jastzeonictodolist.databinding.ActivityContentBinding
import jastzeonic.com.jastzeonictodolist.view.model.TodoEditViewModel

class TodoEditActivity : AppCompatActivity() {
    companion object {
        const val TODO_ITEM_ID = "todo_item_id"
    }

    private lateinit var todoEditViewModel: TodoEditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        todoEditViewModel = ViewModelProviders.of(this).get(TodoEditViewModel::class.java)
        val binding = DataBindingUtil.setContentView<ActivityContentBinding>(this, R.layout.activity_content)
        binding.todoEditViewModel = todoEditViewModel
        setContentView(binding.root)

        val id = intent.getLongExtra(TODO_ITEM_ID, -1)
        if (id != -1L) {
            todoEditViewModel.getTodoModel(id)
            todoEditViewModel.todoId = id
        }
        initViewModelEvent()
    }

    private fun initViewModelEvent() {
        todoEditViewModel.onInsertEvent.observe(this, Observer { isSuccess ->
            if (isSuccess != null && isSuccess) {
                finish()
            }
        })

        todoEditViewModel.onDeleteEvent.observe(this, Observer { isSuccess ->
            if (isSuccess != null && isSuccess) {
                finish()
            }
        })
    }
}