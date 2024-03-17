package co.tiagoaguiar.evernotekt.view

import co.tiagoaguiar.evernotekt.data.model.Note
import co.tiagoaguiar.evernotekt.data.model.NoteState
import io.reactivex.Observable

interface FormView {
    fun render(state: NoteState)
    fun displayNoteIntent(): Observable<Unit>
    fun addNoteIntent(): Observable<Note>
}