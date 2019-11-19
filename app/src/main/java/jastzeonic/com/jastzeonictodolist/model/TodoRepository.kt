package jastzeonic.com.jastzeonictodolist.model

import androidx.room.Room
import android.content.Context
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jastzeonic.com.jastzeonictodolist.RepositoryProvider
import jastzeonic.com.jastzeonictodolist.database.TodoDao
import jastzeonic.com.jastzeonictodolist.database.TodoDatabase

class TodoRepository : RepositoryProvider.DatabaseRepository {


    private lateinit var todoDao: TodoDao
    private lateinit var todoDatabase: TodoDatabase

    override suspend fun init(applicationContext: Context?) {

        if (applicationContext == null) {
            return
        }

        todoDatabase = Room.databaseBuilder(applicationContext, TodoDatabase::class.java, TodoDatabase.DATABASE_NAME)
                .addMigrations(TodoDatabase.MIGRATION_1_2, TodoDatabase.MIGRATION_2_3)
                .build()
        todoDao = todoDatabase.getTodoDao()


    }


    fun getAll(): Flowable<List<TodoModel>> {

        return Flowable.fromCallable { todoDao.getAll() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


    fun getItemById(id: Long): Flowable<TodoModel> {
        return Flowable.fromCallable { todoDao.queryById(id) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

    fun insert(item: TodoModel): Flowable<Long> {
        return Flowable.fromCallable {
            todoDao.insert(item)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun delete(item: TodoModel): Flowable<Int> {
        return Flowable.fromCallable {
            todoDao.delete(item)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}