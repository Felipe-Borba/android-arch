package co.tiagoaguiar.evernotekt.data.model

sealed class NoteState {
    object LoadingState : NoteState()
    data class DataState(val data: List<Note>) : NoteState()
    data class ErrorState(val data: String) : NoteState()
    data class SingleDataState(val data: Note) : NoteState()
    object FinishState : NoteState()
}