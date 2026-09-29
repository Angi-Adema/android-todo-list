package com.coursework.todolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_list")
data class ToDoItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val isCompleted: Boolean = false
)
