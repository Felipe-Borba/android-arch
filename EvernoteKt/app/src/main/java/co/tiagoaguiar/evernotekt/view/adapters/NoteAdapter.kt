package co.tiagoaguiar.evernotekt.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.evernotekt.R
import co.tiagoaguiar.evernotekt.databinding.ListItemNoteBinding
import co.tiagoaguiar.evernotekt.data.model.Note

/**
 *
 * Setembro, 24 2019
 * @author suporte@moonjava.com.br (Tiago Aguiar).
 */
class NoteAdapter(private val notes: List<Note>, val onClickListener: (Note) -> Unit) :
    RecyclerView.Adapter<NoteAdapter.NoteView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteView =
        NoteView(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_note,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: NoteView, position: Int) {
        val note = notes[position]
        holder.binding.note = note
        holder.binding.root.setOnClickListener { onClickListener.invoke(note) }
    }

    override fun getItemCount(): Int = notes.size

    inner class NoteView(val binding: ListItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    // para sobrescrever o binding atual -> @BindingAdapter

}

// EXEMPLOS
/*
@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(int: Int) {
    // or load picasso
    this.setImageDrawable(ContextCompat.getDrawable(context, int))
}

@BindingAdapter("android:text")
fun TextView.setText(s: String) {
    this.text = "$s ola"
}
 */
