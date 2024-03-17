package co.tiagoaguiar.evernotekt.presenter

import co.tiagoaguiar.evernotekt.data.NoteInteractor
import co.tiagoaguiar.evernotekt.data.model.NoteState
import co.tiagoaguiar.evernotekt.view.FormView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FormPresenter( private var interactor: NoteInteractor) {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var view: FormView

    fun bind(view: FormView, id: Int?) {
        this.view = view
        id?.let {
            compositeDisposable.add(observeNoteDisplay(it))
        }
        compositeDisposable.add(observeAddNoteIntent())
    }

    fun unbind() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    private fun observeNoteDisplay(id: Int) = view.displayNoteIntent()
        .flatMap { interactor.getNote(id) }
        .startWith(NoteState.LoadingState)
        .subscribeOn((Schedulers.io()))
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { view.render(it) }

    private fun observeAddNoteIntent() = view.addNoteIntent()
        .flatMap { interactor.createNote(it) }
        .startWith(NoteState.LoadingState)
        .subscribeOn((Schedulers.io()))
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { view.render(it) }

}