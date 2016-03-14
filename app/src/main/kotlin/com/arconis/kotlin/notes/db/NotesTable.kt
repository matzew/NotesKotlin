package com.arconis.kotlin.notes.db

/**
 * Created by benedictp on 10/03/16.
 */
object NotesTable {
    const val TABLE_NAME = "Note"

    const val ID = "_id"
    const val TITLE = "title"
    const val MESSAGE = "message"
    const val USER_ID = "userId"

    fun createStatement() = "CREATE TABLE $TABLE_NAME ($ID Integer Primary Key AutoIncrement, $TITLE Text, $MESSAGE Text, $USER_ID Integer)"

    fun dropStatement() = "DROP TABLE IF EXISTS $TABLE_NAME"
}