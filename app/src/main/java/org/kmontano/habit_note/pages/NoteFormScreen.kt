package org.kmontano.habit_note.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import org.kmontano.habit_note.data.Note

@Composable
fun NoteFormScreen(
    onSave:(Note)-> Unit,
    onEditToForm: Note?= null // Flag is null, save, if not edit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var showErrorDialog by remember { mutableStateOf(false) }

    // fill field with note data
    LaunchedEffect(onEditToForm) {
        if (onEditToForm != null){
            title = onEditToForm.title
            description = onEditToForm.description
        } else {
            title=""
            description=""
        }
        showErrorDialog = false
    }

    Column (
        modifier = Modifier.fillMaxWidth().padding(top = 70.dp).padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text= if (onEditToForm == null) "Add new Note" else "Edit Note", Modifier.padding(bottom = 20.dp))
        OutlinedTextField(
            value = title,
            onValueChange = {title = it},
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = description,
            onValueChange = {description = it},
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        Row {
            Button( onClick = {
                if (title.isBlank() || description.isBlank()){
                    showErrorDialog = true
                } else {
                    val newNote = onEditToForm?.copy(
                        title = title,
                        description = description
                    ) ?: Note(
                        id = (0..1000).random(),
                        title = title,
                        description = description
                    )
                    onSave(newNote)
                }
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Save")
            }
        }
    }

    // show alert if title or description are empty
    if (showErrorDialog){
        AlertDialog(
            onDismissRequest = {showErrorDialog = false},
            title ={Text("Invalid Form")},
            text={Text("Title and Description are required.")},
            confirmButton = {
                Button(onClick = {showErrorDialog = false}) {
                    Text("OK")
                }
            }
        )
    }
}