package com.arconis.kotlin.notes.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.arconis.kotlin.notes.R
import com.arconis.kotlin.notes.data.Note
import com.arconis.kotlin.notes.data.User
import com.arconis.kotlin.notes.util.ParcelableExtra

/**
 * Created by lex on 14.08.15.
 */
class MainActivity : AppCompatActivity(), CreateNoteFragment.CreateNoteListener, MainFragment.MainActions {

    private val user: User by ParcelableExtra(ARG_USER)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            replaceFragment(R.id.main_container, MainFragment.create(user))
        }
    }

    override fun onCreateNewNote() {
        replaceFragment(R.id.main_container, CreateNoteFragment.create(user))
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


fun AppCompatActivity.replaceFragment(containerViewId: Int, fragment: Fragment, backStackName: String? = null) {
    supportFragmentManager.beginTransaction().replace(containerViewId, fragment).addToBackStack(backStackName).commit()
}