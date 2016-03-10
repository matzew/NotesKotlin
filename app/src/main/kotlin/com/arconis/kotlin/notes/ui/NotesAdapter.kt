package com.arconis.kotlin.notes.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.arconis.kotlin.notes.R
import com.arconis.kotlin.notes.data.Note

/**
 * Created by lex on 14.08.15.
 */
class NotesAdapter(context: Context, objects: List<Note>?) : ArrayAdapter<Note>(context, 0, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)

        val holder = view.tag as? ViewHolder ?: createViewHolderFromView(view)
        view.tag = holder

        with(holder) {
            val note = getItem(position)
            title.text = note.title
            content.text = note.content;
        }

        return view;
    }

    private fun createViewHolderFromView(view: View): ViewHolder {
        return ViewHolder(view.findViewById(R.id.note_title) as TextView, view.findViewById(R.id.note_content) as TextView)
    }

    private class ViewHolder(val title: TextView, val content: TextView)
}