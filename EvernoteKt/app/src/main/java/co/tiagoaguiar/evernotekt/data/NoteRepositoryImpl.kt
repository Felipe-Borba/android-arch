package co.tiagoaguiar.evernotekt.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.tiagoaguiar.evernotekt.data.NoteRepository
import co.tiagoaguiar.evernotekt.data.model.Note
import co.tiagoaguiar.evernotekt.data.model.RemoteDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class NoteRepositoryImpl : NoteRepository {

    private val compositeDisposable = CompositeDisposable()
    private val remoteDataSource = RemoteDataSource()

    override fun createNote(note: Note): LiveData<Note> {
        val data = MutableLiveData<Note>()

        val disposableObserver = remoteDataSource.createNote(note)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Note>() {
                override fun onComplete() {
                }

                override fun onNext(note: Note) {
                    data.postValue(note)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    data.postValue(null)
                }
            })

        compositeDisposable.add(disposableObserver)

        return data
    }

    override fun getNote(id: Int) : LiveData<Note?> {
        val data = MutableLiveData<Note?>()

        val disposableObserver = remoteDataSource.getNote(id)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Note>() {
                override fun onComplete() {
                }

                override fun onNext(note: Note) {
                    data.postValue(note)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    data.postValue(null)
                }
            })

        compositeDisposable.add(disposableObserver)

        return data
    }

    override fun getAllNotes() : LiveData<List<Note>?> {
        val data = MutableLiveData<List<Note>?>()

        val disposableObserver = remoteDataSource.listNotes()
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<Note>>() {
                override fun onComplete() {
                }

                override fun onNext(note: List<Note>) {
                    data.postValue(note)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    data.postValue(null)
                }
            })

        compositeDisposable.add(disposableObserver)

        return data
    }

}