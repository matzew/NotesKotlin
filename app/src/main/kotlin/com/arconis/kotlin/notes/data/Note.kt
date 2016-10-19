package com.arconis.kotlin.notes.data

import android.content.ContentValues
import android.database.Cursor
import com.arconis.kotlin.notes.db.NotesTable

/**
 * Created by lex on 07.12.15.
 */
data class Note(var id: Long? = null, val title: String, val content: String, val userId: Long) {

    fun toContentValues() = ContentValues().apply {
        with(NotesTable) {
            put(ID, id)
            put(TITLE, title)
            put(MESSAGE, content)
            put(USER_ID, userId)
        }
    }

}

fun Cursor.toNote(): Note = with(this) {
    return Note(getLong(getColumnIndex(NotesTable.ID)), getString(getColumnIndex(NotesTable.TITLE)), getString(getColumnIndex(NotesTable.MESSAGE)),
            getLong(getColumnIndex(NotesTable.USER_ID)))
}