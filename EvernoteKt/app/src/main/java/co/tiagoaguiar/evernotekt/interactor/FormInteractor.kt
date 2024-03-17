package co.tiagoaguiar.evernotekt.interactor

import androidx.lifecycle.MediatorLiveData
import co.tiagoaguiar.evernotekt.Form
import co.tiagoaguiar.evernotekt.data.NoteRepositoryImpl
import co.tiagoaguiar.evernotekt.data.model.Note

class FormInteractor : Form.Interactor {

    private val saved = MediatorLiveData<Boolean>()
    private val note = MediatorLiveData<Note?>()

    private val repository = NoteRepositoryImpl()

    override fun createNote(note: Note) {
        saved.addSource(repository.createNote(note)) { note ->
            saved.postValue(note != null)
        }
    }

    override fun getNote(id: Int) {
        note.addSource(repository.getNote(id)) { res ->
            note.postValue(res)
        }
    }

    override fun loadNote() = note

    override fun saved() = saved

}