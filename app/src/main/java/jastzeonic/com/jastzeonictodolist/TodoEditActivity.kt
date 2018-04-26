package jastzeonic.com.jastzeonictodolist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import jastzeonic.com.jastzeonictodolist.databinding.ActivityContentBinding
import jastzeonic.com.jastzeonictodolist.view.model.TodoViewModel

class TodoEditActivity : AppCompatActivity() {
    companion object {
        const val TODO_ITEM_ID = "todo_item_id"
    }

    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        todoViewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)
        val binding = DataBindingUtil.setContentView<ActivityContentBinding>(this, R.layout.activity_content)
        binding.todoViewModel = todoViewModel
        setContentView(binding.root)

        val id = intent.getLongExtra(TODO_ITEM_ID, -1)
        if (id != -1L) {
            todoViewModel.getTodoModel(id)
            todoViewModel.todoId = id
        }
        initViewModelEvent()
    }

    private fun initViewModelEvent() {
        todoViewModel.onInsertEvent.observe(this, Observer { isSuccess ->
            if (isSuccess != null && isSuccess) {
                finish()
            }
        })

        todoViewModel.onDeleteEvent.observe(this, Observer { isSuccess ->
            if (isSuccess != null && isSuccess) {
                finish()
            }
        })
    }
}