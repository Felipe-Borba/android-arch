package co.tiagoaguiar.evernotekt.presenter

import co.tiagoaguiar.evernotekt.data.Interactor
import co.tiagoaguiar.evernotekt.data.model.NoteState
import co.tiagoaguiar.evernotekt.view.HomeView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(private var interactor: Interactor) {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var view: HomeView

    fun bind(view: HomeView) {
        this.view = view
        compositeDisposable.add(observeNoteDisplay())
    }

    fun unbind() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    private fun observeNoteDisplay() = view.displayNotesIntent()
        .flatMap { interactor.getAllNotes() }
        .startWith(NoteState.LoadingState)
        .subscribeOn((Schedulers.io()))
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { view.render(it) }

}