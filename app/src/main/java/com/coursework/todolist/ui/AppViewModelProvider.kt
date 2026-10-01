// Following the Android Developers docs - Add repository and Manual DI - Add repository to ViewModel
package com.coursework.todolist.ui

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.coursework.todolist.ToDoApplication
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY

// Create a single AppViewModelProvider object to hold the ViewModel factory
object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {

            // Get the ToDoApplication object from the ViewModel creation context
            val application = this[APPLICATION_KEY] as ToDoApplication

            // Create the ToDoViewModel and provide (dependency injection) the ToDoRepository from the application container
            ToDoViewModel(
                application.appContainer.toDoRepository
            )
        }
    }
}