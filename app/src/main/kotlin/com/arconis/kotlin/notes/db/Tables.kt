package com.arconis.kotlin.notes.db

/**
 * Created by lex on 03.09.15.
 */
object NotesTable {
    const val TABLE_NAME = "Note"

    const val ID = "_id"
    const val TITLE = "title"
    const val MESSAGE = "message"
    const val USER_ID = "userId"

    fun createStatement(): String {
        return "CREATE TABLE $TABLE_NAME ($ID Integer Primary Key AutoIncrement, $TITLE Text, $MESSAGE Text, $USER_ID Integer)"
    }

    fun dropStatement(): String {
        return "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}

object UserTable {
    const val TABLE_NAME = "User"

    const val ID = "_id"
    const val USERNAME = "username"
    const val PASSWORD = "password"

    fun createStatement(): String {
        return "CREATE TABLE $TABLE_NAME ($ID Integer Primary Key AutoIncrement, $USERNAME Text, $PASSWORD Text)"
    }

    fun dropStatement(): String {
        return "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}