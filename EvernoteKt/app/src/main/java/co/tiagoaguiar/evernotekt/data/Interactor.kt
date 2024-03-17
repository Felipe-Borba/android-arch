package co.tiagoaguiar.evernotekt.data

import co.tiagoaguiar.evernotekt.data.model.Note
import co.tiagoaguiar.evernotekt.data.model.NoteState
import io.reactivex.Observable

interface Interactor {

    fun createNote(note: Note) : Observable<NoteState>
    fun getNote(id: Int) : Observable<NoteState>
    fun getAllNotes() : Observable<NoteState>

}