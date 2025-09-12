package org.kmontano.habit_note

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import org.kmontano.habit_note.data.Note
import org.kmontano.habit_note.pages.NoteFormScreen
import org.kmontano.habit_note.pages.NoteListScreen

class MainActivity : ComponentActivity() {
    // Add the note list as mutableState
    private val notes = mutableStateListOf<Note>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var screen by remember { mutableStateOf("list") }
            var onEditToForm by remember { mutableStateOf<Note?>(null) }

            when(screen){
                "list"-> NoteListScreen(
                    notes = notes,
                    onAdd = {
                        onEditToForm = null
                        screen="form"
                     },
                    onEdit = {
                        onEditToForm = it
                        screen="form"
                    },
                    onDelete = {
                        notes.remove(it)
                    }

                )
                "form"-> NoteFormScreen(
                    onEditToForm = onEditToForm,
                    onSave = {
                        if (onEditToForm != null){
                            val index = notes.indexOfFirst{ n-> n.id == it.id}
                            if (index != -1) notes[index] = it
                        } else {
                            notes.add(it)
                        }
                        screen="list"
                    }
                )
            }
        }
    }
}