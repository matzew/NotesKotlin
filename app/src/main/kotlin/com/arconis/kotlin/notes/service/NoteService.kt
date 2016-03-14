package com.arconis.kotlin.notes.service

import android.content.Context
import com.arconis.kotlin.notes.data.Note
import com.arconis.kotlin.notes.data.User
import com.arconis.kotlin.notes.data.toNote
import com.arconis.kotlin.notes.db.NotesDbHelper
import com.arconis.kotlin.notes.db.NotesTable

/**
 * Created by lex on 03.09.15.
 */
class NoteService(private val context: Context) {

    fun saveNote(note: Note) {
        NotesDbHelper(context).writableDatabase.use {
            val id = it.insert(NotesTable.TABLE_NAME, null, note.toContentValues())
            note.id = id
        }
    }

    fun getNotesForUser(user: User): List<Note> {
        NotesDbHelper(context).readableDatabase.use {
            it.query(NotesTable.TABLE_NAME, null, "${NotesTable.USER_ID}=?", arrayOf(user.id?.toString() ?: ""), null, null, null).use {
                val notes = arrayListOf<Note>()
                while (it.moveToNext()) {
                    notes.add(it.toNote())
                }
                return notes
            }
        }
    }
}

