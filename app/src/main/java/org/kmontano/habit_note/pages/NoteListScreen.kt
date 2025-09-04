package org.kmontano.habit_note.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.kmontano.habit_note.data.Note

@Composable
fun NoteListScreen(
    notes: List<Note>,
    onAdd: () -> Unit
){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) {
                Text(text = "Add")
            }
        }
    ){
        padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            items(notes){ note->
                Card (
                    modifier = Modifier.fillMaxSize().padding(8.dp)
                ) {
                    Column (Modifier.padding(16.dp)) {
                        Text(text = note.title, style = MaterialTheme.typography.titleMedium)
                        Text(text = note.description, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}