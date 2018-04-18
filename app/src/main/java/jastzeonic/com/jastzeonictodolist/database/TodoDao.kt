package jastzeonic.com.jastzeonictodolist.database

import android.arch.persistence.room.*
import jastzeonic.com.jastzeonictodolist.model.TodoModel

@Dao
interface TodoDao {

    @Query("select * from " + TodoModel.TABLE_NAME)
    fun getAll(): List<TodoModel>


    @Query("select * from " + TodoModel.TABLE_NAME + " where id LIKE :id")
    fun queryById(id: Long): List<TodoModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: TodoModel):Long

    @Delete
    fun delete(item: TodoModel):Int

}