package co.tiagoaguiar.evernotekt.interactor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import co.tiagoaguiar.evernotekt.Home
import co.tiagoaguiar.evernotekt.data.NoteRepositoryImpl
import co.tiagoaguiar.evernotekt.data.model.Note

class HomeInteractor: Home.Interactor {

    private val noteList = MediatorLiveData<List<Note>?>()
    private val repository = NoteRepositoryImpl()

    init {
        getAllNotes()
    }

    override fun getAllNotes() {
        noteList.addSource(repository.getAllNotes()) { list ->
            noteList.postValue(list)
        }
    }

    override fun loadNoteList() = noteList
}