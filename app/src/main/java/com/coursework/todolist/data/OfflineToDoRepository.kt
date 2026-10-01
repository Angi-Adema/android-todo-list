// Create OfflineToDoRepository following Android Developers Codelabs documentation
package com.coursework.todolist.data

import kotlinx.coroutines.flow.Flow

// Create an OfflineToDoRepository class that implements ToDoRepository using ToDoDao to access the database
class OfflineToDoRepository(
    private val toDoDao: ToDoDao
) : ToDoRepository {

    // Get all the ToDos through the DAO
    override fun getAllToDos(): Flow<List<ToDoItem>> = toDoDao.getAllTasks()

    // Send a new task to the DAO to be added to the database
    override suspend fun insertToDo(task: ToDoItem) {
        toDoDao.insert(task)
    }

    // Update the task by sending it to the DAO to update in the database
    override suspend fun updateToDo(task: ToDoItem) {
        toDoDao.update(task)
    }

    // Send a specific task to the DAO to delete it from the database
    override suspend fun deleteToDo(task: ToDoItem) {
        toDoDao.delete(task)
    }
}