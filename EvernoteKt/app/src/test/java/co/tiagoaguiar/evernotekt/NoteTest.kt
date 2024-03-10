package co.tiagoaguiar.evernotekt

import co.tiagoaguiar.evernotekt.model.Note
import org.junit.Assert.assertEquals
import org.junit.Test

class NoteTest {

    @Test
    fun `Should format date pattern to month and year`() {
        val note = Note(title = "NotaA", body = "NotaA", date = "20/02/2019")
        assertEquals("Fev. 2019", note.createdDate)
    }

    @Test
    fun `Should format date case null`() {
        val note = Note(title = "NotaA", body = "NotaA")
        assertEquals("", note.createdDate)
    }
}