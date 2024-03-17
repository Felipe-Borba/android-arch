package co.tiagoaguiar.evernotekt.presenter

import androidx.lifecycle.Observer
import co.tiagoaguiar.evernotekt.Home
import co.tiagoaguiar.evernotekt.data.model.Note
import co.tiagoaguiar.evernotekt.view.activities.FormActivity
import co.tiagoaguiar.evernotekt.view.activities.HomeActivity
import ru.terrakok.cicerone.Router

class HomePresenter(private var view: Home.View?,
                    private var interactor: Home.Interactor?,
                    private val router: Router?) : Home.Presenter, Home.InteractorOutput {

    override fun onStart() {
        interactor?.loadNoteList()?.observe((view as HomeActivity), Observer { res ->
            if (res != null) {
                onQuerySuccess(res)
            } else {
                onQueryError()
            }
        })
    }

    override fun addNoteClick() {
        router?.navigateTo(FormActivity.TAG)
    }

    override fun onQuerySuccess(data: List<Note>) {
        view?.displayNotes(data)
    }

    override fun onQueryError() {
        view?.displayError("Erro ao carregar dados")
    }
}