package org.kmontano.habit_note

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import org.kmontano.habit_note.data.Note
import org.kmontano.habit_note.pages.NoteFormScreen
import org.kmontano.habit_note.pages.NoteListScreen

class MainActivity : ComponentActivity() {
    // Add the note list as mutableState
    private val notes = mutableListOf<Note>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var screen by remember { mutableStateOf("list") }

            when(screen){
                "list"-> NoteListScreen(
                    notes = notes,
                    onAdd = {screen="form"}
                )
                "form"-> NoteFormScreen(
                    onSave = {
                        notes.add(it)
                        screen="list"
                    }
                )
            }
        }
    }
}