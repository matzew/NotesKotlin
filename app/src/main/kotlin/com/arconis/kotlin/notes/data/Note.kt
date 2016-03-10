package com.arconis.kotlin.notes.data

import android.content.ContentValues
import android.database.Cursor
import com.arconis.kotlin.notes.db.NotesTable

/**
 * Created by lex on 07.12.15.
 */
data class Note(var id: Long?, val title: String, val content: String, var userId: Long) {
    constructor(title: String, content: String, userId: Long) : this(null, title, content, userId)

    constructor() : this(null, "", "", -1)

    fun toContentValues(): ContentValues {
        val values = ContentValues()
        values.put(NotesTable.ID, id)
        values.put(NotesTable.TITLE, title)
        values.put(NotesTable.MESSAGE, content)
        values.put(NotesTable.USER_ID, userId)
        return values
    }
}

fun Cursor.toNote(): Note {
    return Note(this.getLong(this.getColumnIndex(NotesTable.ID)), this.getString(this.getColumnIndex(NotesTable.TITLE)), this.getString(this.getColumnIndex(NotesTable.MESSAGE)),
            this.getLong(this.getColumnIndex(NotesTable.USER_ID)))
}