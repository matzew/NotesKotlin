package com.arconis.kotlin.notes.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.arconis.kotlin.notes.R
import com.arconis.kotlin.notes.data.Note
import com.arconis.kotlin.notes.data.User
import com.arconis.kotlin.notes.utils.ParcelableExtra

/**
 * Created by lex on 14.08.15.
 */
class MainActivity : AppCompatActivity(), CreateNoteFragment.CreateNoteListener, MainFragment.MainActions {
    private val user: User by ParcelableExtra(ARG_USER)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.main_container, MainFragment.create(user)).commit()
        }
    }

    override fun onCreateNewNote() {
        supportFragmentManager.beginTransaction().replace(R.id.main_container, CreateNoteFragment.create(user)).addToBackStack(null).setTransition(FragmentTransaction
                .TRANSIT_FRAGMENT_OPEN)
                .commitAllowingStateLoss()
    }

    override fun onNoteCreated(note: Note) {
        supportFragmentManager.popBackStackImmediate()
    }

    companion object {
        val ARG_USER = "user"

        fun startWithUser(context: Context, user: User) {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(ARG_USER, user)
            context.startActivity(intent)
        }
    }
}
