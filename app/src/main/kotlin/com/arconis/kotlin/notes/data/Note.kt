package com.arconis.kotlin.notes.data

import android.content.ContentValues
import android.database.Cursor
import com.arconis.kotlin.notes.db.NotesTable

/**
 * Created by lex on 07.12.15.
 */
data class Note(var id: Long?, val title: String, val content: String, val userId: Long) {

    constructor(title: String, content: String, userId: Long) : this(null, title, content, userId)

    fun toContentValues() = ContentValues().apply {
        put(NotesTable.ID, id)
        put(NotesTable.TITLE, title)
        put(NotesTable.MESSAGE, content)
        put(NotesTable.USER_ID, userId)
    }

}

fun Cursor.toNote(): Note = with (this) {
    return Note(getLong(getColumnIndex(NotesTable.ID)), getString(getColumnIndex(NotesTable.TITLE)), getString(getColumnIndex(NotesTable.MESSAGE)),
            getLong(getColumnIndex(NotesTable.USER_ID)))
}