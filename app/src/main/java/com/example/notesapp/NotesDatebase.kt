package com.example.notesapp

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

private const val SHARED_PREFENCES_KEY = "SHARED_PREFENCES_KEY"
private const val NOTES_SHARED_PREF = "NOTES_PREF"

class NotesDatebase(
    private val context: Context,
) {
    private val sharedPreferences = context.getSharedPreferences(
        SHARED_PREFENCES_KEY, Context.MODE_PRIVATE

    )

    fun getAllNotes(): List<NotesModel> {
        val gson = Gson()
        val json = sharedPreferences.getString(NOTES_SHARED_PREF, null)
        val type: Type = object : TypeToken<ArrayList<NotesModel?>?>() {}.type
        val noteList = gson.fromJson<ArrayList<NotesModel>>(json, type)
        return noteList ?: emptyList()
    }

    fun saveNote(notesModel: NotesModel) {
        val notes = getAllNotes().toMutableList()
        notes.add(0, notesModel)
        val notesGson = Gson().toJson(notes)
        sharedPreferences
            .edit()
            .putString(NOTES_SHARED_PREF, notesGson)
            .apply()
    }

    fun deleteNoteAtIndex(index: Int) {
        val getAllNotes = getAllNotes().toMutableList()
        if (index in 0 until getAllNotes.size) {
            getAllNotes.removeAt(index)
            val noteGsonString = Gson().toJson(getAllNotes)
            sharedPreferences.edit().putString(SHARED_PREFENCES_KEY, noteGsonString).apply()

            fun delelteAllNotes() = sharedPreferences.edit().clear().apply()
        }
    }
}