package jastzeonic.com.jastzeonictodolist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import jastzeonic.com.jastzeonictodolist.databinding.ActivityContentBinding
import jastzeonic.com.jastzeonictodolist.view.model.ContentViewModel

class ContentActivity : AppCompatActivity() {
    companion object {
        const val TODO_ITEM_ID = "todo_item_id"
    }

    lateinit var contentViewModel: ContentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentViewModel = ViewModelProviders.of(this).get(ContentViewModel::class.java)
        val binding = DataBindingUtil.setContentView<ActivityContentBinding>(this, R.layout.activity_content)
        binding.contentViewModel = contentViewModel
        setContentView(binding.root)

        val id = intent.getLongExtra(TODO_ITEM_ID, -1)
        if (id != -1L) {
            contentViewModel.getTodoModel(id)
            contentViewModel.todoId = id
        }
        initViewModelEvent()
    }

    private fun initViewModelEvent() {
        contentViewModel.onInsertEvent.observe(this, Observer { isSuccess ->
            if (isSuccess != null && isSuccess) {
                finish()
            }
        })

        contentViewModel.onDeleteEvent.observe(this, Observer { isSuccess ->
            if (isSuccess != null && isSuccess) {
                finish()
            }
        })
    }
}