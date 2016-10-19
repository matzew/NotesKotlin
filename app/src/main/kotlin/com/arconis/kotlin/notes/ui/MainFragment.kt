package com.arconis.kotlin.notes.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.arconis.kotlin.notes.R
import com.arconis.kotlin.notes.data.User
import com.arconis.kotlin.notes.service.NoteService
import com.arconis.kotlin.notes.util.ParcelableArgument

/**
 * Created by lex on 03.09.15.
 */
class MainFragment : Fragment() {

    private var notesAdapter: NotesAdapter? = null
    private var listener: MainActions? = null

    private val user: User by ParcelableArgument(ARG_USER)
    private val noteService = lazy { NoteService(activity) }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context !is MainActions) {
            throw IllegalStateException("The attaching activity has to implement ${MainActions::class.java.canonicalName}")
        }
        listener = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        val notes = view.findViewById(R.id.main_list) as ListView
        view.findViewById(R.id.add_note).setOnClickListener { listener?.onCreateNewNote() }

        activity.title = "Notes for ${user.username}"

        val userNotes = noteService.value.getNotesForUser(user)
        notesAdapter = NotesAdapter(activity, userNotes)
        notes.adapter = notesAdapter

        return view
    }

    interface MainActions {
        fun onCreateNewNote()
    }

    companion object {
        val ARG_USER = "user"

        fun create(user: User): MainFragment {
            val fragment = MainFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_USER, user)
            fragment.arguments = bundle
            return fragment
        }
    }
}