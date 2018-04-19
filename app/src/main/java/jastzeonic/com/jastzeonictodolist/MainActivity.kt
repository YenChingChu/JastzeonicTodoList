package jastzeonic.com.jastzeonictodolist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.databinding.ObservableField
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import jastzeonic.com.jastzeonictodolist.databinding.ActivityMainBinding
import jastzeonic.com.jastzeonictodolist.model.TodoModel
import jastzeonic.com.jastzeonictodolist.view.TodoListAdapter
import jastzeonic.com.jastzeonictodolist.view.model.TodoListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    companion object {
        @JvmStatic
        @BindingAdapter("todoList")
        fun setTodoList(recyclerView: RecyclerView, todoList: ObservableField<List<TodoModel>>) {
            if (recyclerView.adapter != null) {
                val adapter = recyclerView.adapter as TodoListAdapter

                adapter.todoList = ArrayList()
                adapter.todoList.addAll(todoList.get() ?: ArrayList())
                adapter.notifyDataSetChanged()
            }
        }
    }


    private lateinit var todoListViewModel: TodoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        todoListViewModel = ViewModelProviders.of(this).get(TodoListViewModel::class.java)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.todoListViewModel = todoListViewModel


        binding.todoList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = TodoListAdapter(this)
        binding.todoList.adapter = adapter

        adapter.onClickEvent.observe(this, Observer { id ->
            val intent = Intent(MainActivity@ this, TodoEditActivity::class.java)
            intent.putExtra(TodoEditActivity.TODO_ITEM_ID, id)
            startActivity(intent)
        })

        adapter.onDeleteEvent.observe(this, Observer { id ->
            if (id != null) {
                todoListViewModel.deleteTodoModel(id)
            }

        })



        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()


        todoListViewModel.getTodoList()


        add_button.setOnClickListener({
            val intent = Intent(MainActivity@ this, TodoEditActivity::class.java)
            startActivity(intent)
        })
    }


}

