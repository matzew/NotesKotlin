package com.arconis.kotlin.notes.db

/**
 * Created by benedictp on 10/03/16.
 */
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