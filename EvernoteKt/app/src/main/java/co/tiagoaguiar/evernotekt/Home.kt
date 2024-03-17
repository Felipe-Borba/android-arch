package co.tiagoaguiar.evernotekt

import androidx.lifecycle.LiveData
import co.tiagoaguiar.evernotekt.data.model.Note

interface Home {

    interface View {
        fun displayNotes(notes: List<Note>)
        fun displayError(message: String)
    }

    interface Presenter {
        fun onStart()
        fun addNoteClick()
    }

    interface Interactor {
        fun getAllNotes()
        fun loadNoteList(): LiveData<List<Note>?>
    }

    interface InteractorOutput {
        fun onQuerySuccess(data: List<Note>)
        fun onQueryError()
    }

}