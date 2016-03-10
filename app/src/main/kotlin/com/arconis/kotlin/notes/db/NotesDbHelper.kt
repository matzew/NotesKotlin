package com.arconis.kotlin.notes.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by lex on 03.09.15.
 */
class NotesDbHelper(private val context: Context) : SQLiteOpenHelper(context, "kotlin_notes.db", null, 1) {
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        with(db) {
            execSQL(NotesTable.dropStatement())
            execSQL(UserTable.dropStatement())
        }
        onCreate(db)
    }

    override fun onCreate(db: SQLiteDatabase) {
        with(db) {
            execSQL(NotesTable.createStatement())
            execSQL(UserTable.createStatement())
        }
    }
}

