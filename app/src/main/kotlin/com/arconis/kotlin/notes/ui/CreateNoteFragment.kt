package com.arconis.kotlin.notes.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.EditText
import com.arconis.kotlin.notes.R
import com.arconis.kotlin.notes.data.Note
import com.arconis.kotlin.notes.data.User
import com.arconis.kotlin.notes.service.NoteService
import com.arconis.kotlin.notes.util.FragmentView
import com.arconis.kotlin.notes.util.ParcelableArgument
import com.arconis.kotlin.notes.util.value

/**
 * Created by lex on 03.09.15.
 */
class CreateNoteFragment : Fragment() {

    private val title: EditText by FragmentView(R.id.note_title)
    private val content: EditText by FragmentView(R.id.note_content)

    private val user: User by ParcelableArgument(ARG_USER)

    private var listener: CreateNoteListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context !is CreateNoteListener) {
            throw IllegalStateException("The attaching activity has to implement ${CreateNoteListener::class.java.canonicalName}")
        }
        listener = context
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater?.inflate(R.layout.create_note_fragment, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.create_note_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.create_note_save) {
            saveNote()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveNote() {
        val note = Note(title = title.value(), content = content.value(), userId = user.id ?: -1)
        NoteService(activity).saveNote(note)
        listener?.onNoteCreated(note)
    }

    interface CreateNoteListener {
        fun onNoteCreated(note: Note)
    }

    companion object {
        val ARG_USER = "user"

        fun create(user: User): CreateNoteFragment {
            val fragment = CreateNoteFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_USER, user)
            fragment.arguments = bundle
            return fragment
        }
    }
}