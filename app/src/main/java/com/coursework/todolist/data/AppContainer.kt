// Create a container to hold the dependencies required for the application. Built referencing Android
// developers Add repository and Manual DI documentation (Create an application container)
package com.coursework.todolist.data

import android.content.Context

// Create AppContainer interface to define the dependencies needing to be provided by classes
// implementing AppContainer
interface AppContainer {

    // Dependency ToDoRepository is being made available, not constructing the object
    val toDoRepository: ToDoRepository

}

// Implement AppContainer and pass the Android Context to DefaultAppContainer for accessing the Room database
class DefaultAppContainer(
    private val context: Context
) : AppContainer {

    // Since AppContainer requires ToDoRepository we provide it here and only create it when needed
    override val toDoRepository: ToDoRepository by lazy {

        // Create the OfflineToDoRepository instance by providing the required ToDoDao
        OfflineToDoRepository(

            // Obtain the Room database through the Context and get the ToDoDao object
            ToDoDatabase.getDatabase(context).toDoDao()
        )
    }
}