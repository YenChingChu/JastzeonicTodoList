package jastzeonic.com.jastzeonictodolist.view

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import jastzeonic.com.jastzeonictodolist.TodoEditActivity
import jastzeonic.com.jastzeonictodolist.R
import jastzeonic.com.jastzeonictodolist.model.TodoModel

class TodoListAdapter(private val context: Context) : RecyclerView.Adapter<TodoViewHolder>() {


    val onDeleteEvent = MutableLiveData<Long>()
    val onClickEvent = MutableLiveData<Long>()

    var todoList: MutableList<TodoModel> = ArrayList()


    interface AdapterOnClickListener {
        fun onClick(position: Int)
        fun onItemLongClick(position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {

        val inflater = LayoutInflater.from(context)

        return TodoViewHolder(inflater.inflate(R.layout.layout_item_todo, parent, false), adapterClickListener)

    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.todoContent.text = todoList[position].todoContent
        holder.todoDescription.text = todoList[position].todoDescription
    }


    private val adapterClickListener = object : AdapterOnClickListener {
        override fun onClick(position: Int) {

            onClickEvent.value = todoList[position].id
        }

        override fun onItemLongClick(position: Int) {

            onDeleteEvent.value = todoList[position].id

            todoList.removeAt(position)
            notifyItemRemoved(position)

        }


    }

}