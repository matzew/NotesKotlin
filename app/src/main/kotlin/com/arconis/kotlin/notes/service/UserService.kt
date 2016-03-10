package com.arconis.kotlin.notes.service

import android.content.Context
import com.arconis.kotlin.notes.data.User
import com.arconis.kotlin.notes.data.toUser
import com.arconis.kotlin.notes.db.NotesDbHelper
import com.arconis.kotlin.notes.db.UserTable

/**
 * Created by lex on 03.09.15.
 */
class UserService(private val context: Context) {
    fun getUserByName(username: String): User? {
        NotesDbHelper(context).readableDatabase.use {
            it.query(UserTable.TABLE_NAME, null, "${UserTable.USERNAME}=?", arrayOf(username), null, null, null).use {
                if (it.moveToFirst()) {
                    return it.toUser()
                } else {
                    return null
                }
            }
        }
    }

    fun doesUserExist(username: String): Boolean {
        NotesDbHelper(context).readableDatabase.use {
            it.query(UserTable.TABLE_NAME, arrayOf("count(${UserTable.USERNAME})"), "${UserTable.USERNAME}=?", arrayOf(username), null, null, null).use {
                return it.moveToFirst() && it.getInt(0) > 0
            }
        }
    }

    fun saveUser(user: User) {
        NotesDbHelper(context).writableDatabase.use {
            val id = it.insert(UserTable.TABLE_NAME, null, user.toContentValues())
            user.id = id
        }
    }
}