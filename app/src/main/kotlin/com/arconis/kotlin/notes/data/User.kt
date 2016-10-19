package com.arconis.kotlin.notes.data

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcel
import android.os.Parcelable
import com.arconis.kotlin.notes.db.UserTable

/**
 * Created by lex on 28.08.15.
 */
data class User(var id: Long? = null, val username: String, val password: String) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readLong().nullOrId(), parcel.readString(), parcel.readString())

    fun toContentValues() = ContentValues().apply {
        put(UserTable.ID, id)
        put(UserTable.USERNAME, username)
        put(UserTable.PASSWORD, password)
    }

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id ?: -1)
        writeString(username)
        writeString(password)
    }

    override fun describeContents() = 0

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel) = User(source)

            override fun newArray(size: Int) = arrayOfNulls<User>(size)
        }
    }
}

fun Cursor.toUser(): User = with(this) {
    return User(getLong(getColumnIndex(UserTable.ID)), getString(getColumnIndex(UserTable.USERNAME)), getString(getColumnIndex(UserTable.PASSWORD)))
}


private fun Long.nullOrId() = if (this == -1L) null else this
