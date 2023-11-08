package com.example.notesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.databinding.NotesItemBinding

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private val notesList = mutableListOf<NotesModel>()

    fun updateList(noteList: List<NotesModel>) {
        this.notesList.clear()
        this.notesList.addAll(noteList)
        notifyDataSetChanged()
    }

    inner class NotesViewHolder(
        private val binding: NotesItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(notesModel: NotesModel) {
            notesList.indexOf(notesModel)
            binding.tvNotetitle.text = notesModel.noteTitle
            binding.tvNoteoverView.text = notesModel.noteDescription
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): NotesViewHolder {
        val binding = NotesItemBinding.bind(
            LayoutInflater.from(parent.context).inflate(
                R.layout.notes_item,
                parent,
                false,
            )
        )
        return NotesViewHolder(binding)
    }

    override fun getItemCount(): Int = notesList.size

    override fun onBindViewHolder(
        holder: NotesViewHolder,
        position: Int,
    ) {
        holder.bind(notesList[position])
    }

}


