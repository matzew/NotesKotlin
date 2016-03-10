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
        values.put(UserTable.ID, id)
        values.put(UserTable.USERNAME, username)
        values.put(UserTable.PASSWORD, password)
        return values
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id ?: -1)
        dest.writeString(username)
        dest.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        public val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User {
                return User(source)
            }

            override fun newArray(size: Int): Array<User> {
                return arrayOf()
            }
        }
    }
}

fun Cursor.toUser(): User {
    return User(this.getLong(this.getColumnIndex(UserTable.ID)), this.getString(this.getColumnIndex(UserTable.USERNAME)), this.getString(this.getColumnIndex(UserTable.PASSWORD)))
}

private fun Long.nullOrId(): Long? {
    if (this == -1L) return null else return this
}
