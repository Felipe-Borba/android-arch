package co.tiagoaguiar.evernotekt

import co.tiagoaguiar.evernotekt.add.Add
import co.tiagoaguiar.evernotekt.add.presentation.AddPresenter
import co.tiagoaguiar.evernotekt.home.Home
import co.tiagoaguiar.evernotekt.home.presentation.HomePresenter
import co.tiagoaguiar.evernotekt.model.Note
import co.tiagoaguiar.evernotekt.model.RemoteDataSource
import com.nhaarman.mockito_kotlin.anyOrNull
import io.reactivex.Observable
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AddPresenterTests : BaseTest() {

    @Rule
    @JvmField
    var testSchedulerRule = RxSchedulerRule()

    @Mock
    private lateinit var mockView: Add.View

    @Mock
    private lateinit var mockDataSource: RemoteDataSource

    @Captor
    private lateinit var noteArgCaptor: ArgumentCaptor<Note>

    lateinit var addPresenter: AddPresenter

    @Before
    fun setup() {
        addPresenter = AddPresenter(mockView, mockDataSource)
    }

    @Test
    fun `test must not add note with empty body`() {
        // When
        addPresenter.createNote("", "")

        // Then
        Mockito.verify(mockView).displayError("Título e corpo da nota deve ser informado")
    }

    @Test
    fun `test must add note`() {
        // Given
        val note = Note(title = "NotaA", body = "Corpo NotaA")
        Mockito.doReturn(Observable.just(note)).`when`(mockDataSource).createNote(captureArg(
            noteArgCaptor
        ))

        // When
        addPresenter.createNote("NotaA", "Corpo NotaA")

        // Then
        Mockito.verify(mockDataSource).createNote(captureArg(noteArgCaptor))

        Assert.assertThat(noteArgCaptor.value.title, CoreMatchers.equalTo("NotaA"))
        Assert.assertThat(noteArgCaptor.value.body, CoreMatchers.equalTo("Corpo NotaA"))

        Mockito.verify(mockView).returnToHome()
    }

    @Test
    fun `test must show error message when creation failure`() {
        // Given
        Mockito.doReturn(Observable.error<Throwable>(Throwable("server error"))).`when`(
            mockDataSource
        ).createNote(anyOrNull())

        // When
        addPresenter.createNote("NotaA", "Corpo NotaA")

        // Then
        Mockito.verify(mockDataSource).createNote(captureArg(noteArgCaptor))
        Assert.assertThat(noteArgCaptor.value.title, CoreMatchers.equalTo("NotaA"))
        Assert.assertThat(noteArgCaptor.value.body, CoreMatchers.equalTo("Corpo NotaA"))

        Mockito.verify(mockView).displayError("Erro ao criar a nota")
    }

}