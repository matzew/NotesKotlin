package com.arconis.kotlin.notes.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.arconis.kotlin.notes.data.Note;
import com.arconis.kotlin.notes.data.NoteKt;
import com.arconis.kotlin.notes.data.User;
import com.arconis.kotlin.notes.db.NotesDbHelper;
import com.arconis.kotlin.notes.db.NotesTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lex on 07.12.15.
 */
public class JavaNoteService {

	private final Context context;

	public JavaNoteService(Context context) {
		this.context = context;
	}

	public void saveNote(Note note) {
		SQLiteDatabase db = new NotesDbHelper(context).getWritableDatabase();
		try {
			long id = db.insert(NotesTable.TABLE_NAME, null, note.toContentValues());
			note.setId(id);
		} finally {
			db.close();
		}
	}

	public List<Note> getNotesForUser(User user) {
		SQLiteDatabase db = new NotesDbHelper(context).getReadableDatabase();
		Cursor cursor = null;
		try {
			Long id = user.getId();
			String idParam = "";
			if (id != null) {
				idParam = id.toString();
			}
			cursor = db.query(NotesTable.TABLE_NAME, null, NotesTable.USER_ID + "=?", new String[]{idParam}, null, null, null);
			ArrayList<Note> notes = new ArrayList<>();
			while (cursor.moveToNext()) {
				notes.add(NoteKt.toNote(cursor));
			}
			return notes;
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			db.close();
		}
	}
}
