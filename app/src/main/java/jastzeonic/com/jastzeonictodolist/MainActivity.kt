package jastzeonic.com.jastzeonictodolist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import jastzeonic.com.jastzeonictodolist.model.TodoRepository
import jastzeonic.com.jastzeonictodolist.view.TodoListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal var test = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        val repo = RepositoryProvider.getDatabaseRepository(TodoRepository::class.java)



        repo.getAll().subscribe({ list ->

            todo_list.layoutManager = LinearLayoutManager(MainActivity@ this, LinearLayoutManager.VERTICAL, false)
            todo_list.adapter = TodoListAdapter(ainActivity@ this, list)


        }, { onError -> onError })


        add_button.setOnClickListener({
            val intent = Intent(MainActivity@ this, ContentActivity::class.java)
            startActivity(intent)
        })
    }



}

