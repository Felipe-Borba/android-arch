package co.tiagoaguiar.evernotekt.presenter

import androidx.lifecycle.Observer
import co.tiagoaguiar.evernotekt.Form
import co.tiagoaguiar.evernotekt.data.model.Note
import co.tiagoaguiar.evernotekt.view.activities.FormActivity
import ru.terrakok.cicerone.Router

class FormPresenter(
    private var view: Form.View?,
    private var interactor: Form.Interactor?,
    private val router: Router?
) : Form.Presenter, Form.InteractorOutput {

    override fun onStart(noteId: Int?) {
        interactor?.saved()?.observe((view as FormActivity), Observer { saved ->
            if (saved) {
                onSaveSuccess()
            } else {
                onSaveError()
            }
        })

        interactor?.loadNote()?.observe((view as FormActivity), Observer { res ->
            if (res != null) {
                onQuerySuccess(res)
            } else {
                onQueryError()
            }
        })

        noteId?.let { interactor?.getNote(it) }
    }

    override fun backPressClick() {
        router?.exit()
    }

    override fun saveNote(title: String, body: String) {
        if (title.isEmpty() || body.isEmpty()) {
            view?.displayError("Título e nota deve ser informado")
            return
        }

        interactor?.createNote(Note(title = title, body = body))
    }

    override fun onSaveSuccess() {
        router?.exit()
    }

    override fun onSaveError() {
        view?.displayError("Erro ao salvar nota")
    }

    override fun onQuerySuccess(note: Note) {
        view?.displayNote(note)
    }

    override fun onQueryError() {
        view?.displayError("Erro ao carregar dados")
    }

}