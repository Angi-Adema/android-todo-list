// Create the Room database class using the Android Developers documentation (codelabs)
package com.coursework.todolist.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room

// Create the Room database using ToDoItem as the entity and set the database version to 1
@Database(entities = [ToDoItem::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {

    // Provide access to the DAO providing database operations
    abstract fun toDoDao(): ToDoDao

    // Implement a Singleton or single database instance used by the application
    companion object {

        // Maintain visibility of changes to the database instance across various threads
        @Volatile
        private var INSTANCE: ToDoDatabase? = null

        // Return the database instance or create one if there isn't one
        fun getDatabase(context: Context): ToDoDatabase {
            return INSTANCE ?: synchronized(this) {

                // Create the Room database using the provided context
                val instance = INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    "todo_database"
                ).build()

                // Store and return the database instance
                INSTANCE = instance
                instance
            }
        }
    }
}