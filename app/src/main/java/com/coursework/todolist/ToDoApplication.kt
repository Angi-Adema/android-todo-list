// Create an application level class that will provide dependencies for the application
package com.coursework.todolist

import android.app.Application
import com.coursework.todolist.data.AppContainer
import com.coursework.todolist.data.DefaultAppContainer

// Create the ToDoApplication class that inherits Android's Application object making it a Context
class ToDoApplication : Application() {

    // Provide the AppContainer using DefaultAppContainer to be created when needed
    val appContainer: AppContainer by lazy {

        // Call DefaultAppContainer and pass this application as Context into it
        DefaultAppContainer(this)
    }
}
