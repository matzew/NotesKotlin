package com.arconis.kotlin.notes.data

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcel
import android.os.Parcelable
import com.arconis.kotlin.notes.db.UserTable

/**
 * Created by lex on 28.08.15.
 */
data class User(var id: Long?, val username: String, val password: String) : Parcelable {

    constructor(username: String, password: String) : this(null, username, password)

    constructor(parcel: Parcel) : this(parcel.readLong().nullOrId(), parcel.readString(), parcel.readString())

    fun toContentValues(): ContentValues {
        val values = ContentValues()
        with (UserTable) {
            values.put(ID, id)
            values.put(USERNAME, username)
            values.put(PASSWORD, password)
        }
        return values
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with (dest) {
            writeLong(id ?: -1)
            writeString(username)
            writeString(password)
        }
    }

    override fun describeContents() = 0

    companion object {
        @JvmField final val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel) = User(source)

            override fun newArray(size: Int): Array<User?> {
                return arrayOfNulls(size)
            }
        }
    }
}

fun Cursor.toUser(): User {
    with(this) {
        return User(getLong(getColumnIndex(UserTable.ID)), getString(getColumnIndex(UserTable.USERNAME)), getString(getColumnIndex(UserTable.PASSWORD))
        )
    }
}

private fun Long.nullOrId(): Long? {
    if (this == -1L) return null else return this
}
