// Create a repository interface following the Android Developers Codelabs documentation
package com.coursework.todolist.data

import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    // Get all ToDo tasks from the database
    fun getAllToDos(): Flow<List<ToDoItem>>

    // Add a new task to the database
    suspend fun insertToDo(task: ToDoItem)

    // Update an existing task in the database
    suspend fun updateToDo(task: ToDoItem)

    // Delete a task from the database
    suspend fun deleteToDo(task: ToDoItem)
}