package co.tiagoaguiar.evernotekt.viewmodel

import androidx.lifecycle.ViewModel
import co.tiagoaguiar.evernotekt.data.NoteRepository
import co.tiagoaguiar.evernotekt.data.NoteRepositoryImpl

class HomeViewModel(private val repository: NoteRepository = NoteRepositoryImpl())  : ViewModel() {
    
    fun getAllNotes() = repository.getAllNotes()

}