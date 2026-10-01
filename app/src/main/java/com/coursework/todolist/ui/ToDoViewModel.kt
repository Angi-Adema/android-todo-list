// Create the ViewModel using Android Developers Add repository and Manual DI - Add repository to ViewModel
// and Android Developers Read and update data with Room docs
package com.coursework.todolist.ui

import androidx.lifecycle.ViewModel
import com.coursework.todolist.data.ToDoItem
import com.coursework.todolist.data.ToDoRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// Create a UI state container holding the list of ToDos that will be sent to the UI and displayed on the screen
data class ToDoUiState(
    val toDoList: List<ToDoItem> = emptyList()
)

// Utilize Android's ViewModel to manage the data and the state needed by the app UI
// ToDoViewModel requires a ToDoRepository and when the application creates this ViewModel it must also have a ToDoRepository
class ToDoViewModel(
    private val toDoRepository: ToDoRepository
) : ViewModel() {

    // Obtain the ToDos Flow from the repository and send it to the UI as a StateFlow
    val toDoUiState: StateFlow<ToDoUiState> =

        // Convert the List<ToDoItem> received from the repository into a ToDoUiState object
        toDoRepository.getAllToDos().map { ToDoUiState(it) }

            // Change the Flow into a StateFlow for the current UI state
            .stateIn(
                scope = viewModelScope,   // Run the StateFlow within the lifecycle of this ViewModel
                started = SharingStarted.WhileSubscribed(5_000L),   // Maintain active Flow while UI is subscribed and shortly after it ends
                initialValue = ToDoUiState()   // Ensure an empty ToDoUiState until it receives the first list from the repository
            )

    // Insert a ToDo by passing the ToDoItem to the repository
    fun insertToDo(task: ToDoItem) {

        // Launch a coroutine within the lifecycle of this ViewModel
        viewModelScope.launch {

            // Call the repository insert function and pass the ToDo to the DAO
            toDoRepository.insertToDo(task)
        }
    }

    // Update a ToDo item by passing the updated ToDoItem to the repository
    fun updateToDo(task: ToDoItem) {

        // Launch a coroutine within the lifecycle of this ViewModel
        viewModelScope.launch {

            // Call the repository update function and pass the updated ToDo to the DAO
            toDoRepository.updateToDo(task)
        }
    }

    // Delete a ToDo item by passing the ToDo to be deleted to the repository
    fun deleteToDo(task: ToDoItem) {

        // Launch a coroutine within the lifecycle of this ViewModel
        viewModelScope.launch {

            // Call the repository delete function and pass the ToDo to the DAO
            toDoRepository.deleteToDo(task)
        }
    }
}