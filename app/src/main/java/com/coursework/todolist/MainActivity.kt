package com.coursework.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.coursework.todolist.ui.theme.ToDoListTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coursework.todolist.ui.AppViewModelProvider
import com.coursework.todolist.ui.ToDoViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Button
import com.coursework.todolist.data.ToDoItem
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Alignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoListTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    ToDoScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// Using Android Developers Read and update data with Room - Display the Inventory data we created the
// ToDo screen and connected it to ToDoViewModel using the ViewModel factory
@Composable
fun ToDoScreen(
    modifier: Modifier = Modifier,
    viewModel: ToDoViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    )
) {
    // Observe the StateFlow from ToDoViewModel as Compose State
    val toDoUiState by viewModel.toDoUiState.collectAsState()

    // Store the text the user enters to create a ToDo (Android Developers docs - State and Jetpack Compose)
    var newToDoTitle by remember { mutableStateOf("") }

    // Wrap screen elements in a column
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Create a text field so the user can enter the title of a new ToDo
        OutlinedTextField(
            value = newToDoTitle,
            onValueChange = { newToDoTitle = it },
            label = { Text("New ToDo") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Button to add the new ToDo item to the list upon clicking "Add" (Android developers docs - button)
        Button(
            onClick = {
                if (newToDoTitle.isNotBlank()) {
                    viewModel.insertToDo(
                        ToDoItem(title = newToDoTitle)
                    )
                    newToDoTitle = ""    // Clear the text field after ToDo submitted
                }
            }
        ) {
            Text("Add")
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Display the ToDo list retrieved from the ViewModel (Android Developers docs - Lazy Lists and Lazy Grids)
        LazyColumn {

            items(toDoUiState.toDoList) { task ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = task.isCompleted,
                            onCheckedChange = { isChecked ->
                                viewModel.updateToDo(
                                    task.copy(isCompleted = isChecked)
                                )
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        Text(text = task.title, modifier = Modifier.weight(1f))
                    }
                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            viewModel.deleteToDo(task)
                        }
                    ) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}

