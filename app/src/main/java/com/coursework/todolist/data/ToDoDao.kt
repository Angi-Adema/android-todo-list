// Create the Room DAO interface defining database operations using Android Developers documentation
package com.coursework.todolist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Insert
    suspend fun insert(task: ToDoItem)

    @Delete
    suspend fun delete(task: ToDoItem)

    @Update
    suspend fun update(task: ToDoItem)

    @Query("SELECT * from todo_list")
    fun getAllTasks(): Flow<List<ToDoItem>>

}