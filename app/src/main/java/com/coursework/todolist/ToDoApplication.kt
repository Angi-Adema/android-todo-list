// Create an application level class that will provide dependencies for the application following
// Android Developers Add repository and Manual DI codelab.
package com.coursework.todolist

import android.app.Application
import com.coursework.todolist.data.AppContainer
import com.coursework.todolist.data.DefaultAppContainer

// Create the ToDoApplication class that inherits Android's Application object making it a Context
class ToDoApplication : Application() {

    // Declare the appContainer object that will be initialized upon application creation
    lateinit var appContainer: AppContainer

    // When the app is created, initialize appContainer
    override fun onCreate() {
        super.onCreate()   // Calls the parent Application class's onCreate() before initialization

        // Initialize appContainer with DefaultAppContainer passing in the application Context
        appContainer = DefaultAppContainer(this)
    }

}
