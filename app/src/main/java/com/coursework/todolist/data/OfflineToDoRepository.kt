package com.coursework.todolist.data

import kotlinx.coroutines.flow.Flow

class OfflineToDoRepository(
    private val toDoDao: ToDoDao
) : ToDoRepository {

    override fun getAllToDos(): Flow<List<ToDoItem>> = toDoDao.getAllTasks()

    override suspend fun insertToDo(task: ToDoItem) {
        toDoDao.insert(task)
    }
}