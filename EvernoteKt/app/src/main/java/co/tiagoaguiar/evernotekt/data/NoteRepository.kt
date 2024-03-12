package co.tiagoaguiar.evernotekt.data

import androidx.lifecycle.LiveData
import co.tiagoaguiar.evernotekt.data.model.Note

interface NoteRepository {
    fun createNote(note: Note) : LiveData<Note>
    fun getNote(id: Int) : LiveData<Note?>
    fun getAllNotes() : LiveData<List<Note>?>
}