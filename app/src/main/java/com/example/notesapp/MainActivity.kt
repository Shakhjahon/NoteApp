package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.notesapp.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val sharedPref: NotesDatebase by lazy {
        NotesDatebase(this)
    }

    private val adapter: NotesAdapter by lazy {
        NotesAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViewsAndAdapter()
        setOnClickListner()
    }

    private fun setupViewsAndAdapter(){
        val listofNotes = sharedPref.getAllNotes()
        if (listofNotes.isNotEmpty()){
            binding.notEmptyImg.visibility = View.GONE
            binding.recucleView.visibility = View.VISIBLE
            adapter.updateList(listofNotes)
            binding.recucleView.adapter = adapter
        }
    }

            private fun setOnClickListner() = binding.apply {
        floatingActionButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddNotesActivity::class.java))
        }
        deleteCard.setOnClickListener {
            showconfirmDeleteNoteDiolog()
        }
    }

    private fun showconfirmDeleteNoteDiolog() {
        val alertDiolog = MaterialAlertDialogBuilder(this)
        alertDiolog.setMessage("Do you want to delete all Notes?")
        alertDiolog.setPositiveButton("Yes") { dialog, _ ->
            deleteAllSavedNotes()
            dialog.dismiss()
        }
        alertDiolog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        alertDiolog.create().show()
    }

    private fun deleteAllSavedNotes() {
        adapter.updateList((emptyList()))
        binding.notEmptyImg.visibility = View.VISIBLE
        binding.recucleView.visibility = View.GONE
    }
}