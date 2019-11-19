package jastzeonic.com.jastzeonictodolist.database

import androidx.room.*
import jastzeonic.com.jastzeonictodolist.model.TodoModel

@Dao
interface TodoDao {

    @Query("select * from " + TodoModel.TABLE_NAME)
    fun getAll(): List<TodoModel>


    @Query("select * from " + TodoModel.TABLE_NAME + " where id LIKE :id LIMIT 1")
    fun queryById(id: Long): TodoModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: TodoModel):Long

    @Update
    fun update(item: TodoModel):Int


    @Delete
    fun delete(item: TodoModel):Int

}