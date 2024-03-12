package co.tiagoaguiar.evernotekt.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.tiagoaguiar.evernotekt.data.NoteRepository
import co.tiagoaguiar.evernotekt.data.NoteRepositoryImpl
import co.tiagoaguiar.evernotekt.data.model.Note

class AddViewModel(private val repository: NoteRepository = NoteRepositoryImpl()) : ViewModel() {

    private val saveLiveData = MutableLiveData<Boolean>()

    var title = ObservableField("")
    var body = ObservableField("")

    val saved: LiveData<Boolean>
        get() = saveLiveData

    fun createNote() {
        if (title.get().isNullOrEmpty() || body.get().isNullOrEmpty()) {
            saveLiveData.value = false
            return
        }

        val note = Note(title = title.get(), body = body.get())
        repository.createNote(note)
        saveLiveData.value = true
    }

    fun getNote(id: Int) = repository.getNote(id)

}