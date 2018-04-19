package jastzeonic.com.jastzeonictodolist.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import jastzeonic.com.jastzeonictodolist.R

class TodoViewHolder(view: View, adapterOnClickListener: TodoListAdapter.AdapterOnClickListener) : RecyclerView.ViewHolder(view) {


    val todoContent: TextView = view.findViewById(R.id.todo_content)
    val todoDescription: TextView = view.findViewById(R.id.todo_description)

    init {
        view.setOnClickListener {
            adapterOnClickListener.onClick(adapterPosition)

        }
        view.setOnLongClickListener({
            adapterOnClickListener.onItemLongClick(adapterPosition)
            true
        })
    }


}