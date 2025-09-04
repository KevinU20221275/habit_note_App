package org.kmontano.habit_note.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import org.kmontano.habit_note.data.Note

@Composable
fun NoteFormScreen(
    onSave:(Note)-> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column (
        modifier = Modifier.fillMaxWidth().padding(top = 40.dp)
    ) {
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button( onClick = {
                val note = Note(
                    id=(0..1000).random(),
                    title=title,
                    description=description
                )
                onSave(note)
            }) {
                Text("Save")
            }
        }
    }
}