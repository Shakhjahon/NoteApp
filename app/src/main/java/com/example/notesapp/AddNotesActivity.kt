package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notesapp.databinding.ActivityAddNotesBinding
import com.google.android.material.snackbar.Snackbar

class AddNotesActivity : AppCompatActivity() {

    private val binding: ActivityAddNotesBinding by lazy {
        ActivityAddNotesBinding.inflate(layoutInflater)
    }
    private val sharedPref: NotesDatebase by lazy {
        NotesDatebase(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.saveCard.setOnClickListener {
            saveNotes()
        }
        binding.backCard.setOnClickListener{
            finish()
        }
    }

    private fun saveNotes() = binding.apply {
        if (titleEt.text?.isNotEmpty() == true && overViewEt.text?.isNotEmpty() == true) {
            sharedPref.saveNote(
                NotesModel(
                    noteTitle = titleEt.text.toString(),
                    noteDescription = overViewEt.text.toString()
                )
            )
            startActivity(Intent(this@AddNotesActivity, MainActivity::class.java))
        } else showToastMassage("Заполните все Поля")
    }

    private fun showToastMassage(massage: String) {
        Snackbar.make(
            binding.root,
            massage,
            Snackbar.LENGTH_SHORT,
        ).show()
    }

}