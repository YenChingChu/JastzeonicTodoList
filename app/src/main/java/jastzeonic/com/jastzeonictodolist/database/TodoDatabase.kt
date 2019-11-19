package jastzeonic.com.jastzeonictodolist.database

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import android.util.Log
import jastzeonic.com.jastzeonictodolist.model.TodoModel


@Database(entities = [(TodoModel::class)], version = 3)
abstract class TodoDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "todo_database"

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                val tableName = TodoModel.TABLE_NAME
                database.execSQL("ALTER TABLE $tableName ADD COLUMN imageUrl TEXT NOT NULL DEFAULT ''")
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {

                val tableName = TodoModel.TABLE_NAME
                val id = TodoModel.ID_NAME
                val todoContent = TodoModel.TODO_CONTENT_NAME
                val todoDescription = TodoModel.TODO_DESCRIPTION_NAME
                val isFinished = TodoModel.IS_FINISHED_NAME
                val timestamp = TodoModel.TIMESTAMP_NAME


                database.execSQL("CREATE TABLE temple ($id INTEGER NOT NULL DEFAULT '', $todoContent TEXT NOT NULL DEFAULT '', $todoDescription TEXT NOT NULL DEFAULT '', $isFinished INTEGER NOT NULL DEFAULT '', $timestamp TEXT NOT NULL DEFAULT '', PRIMARY KEY($id))");


                database.execSQL("INSERT INTO temple ($id, $todoContent, $todoDescription, $isFinished, $timestamp) SELECT $id, $todoContent, $todoDescription, $isFinished, $timestamp FROM $tableName")

                database.execSQL("DROP TABLE $tableName")


                database.execSQL("ALTER TABLE temple RENAME TO $tableName")

            }
        }


    }

    abstract fun getTodoDao(): TodoDao


}