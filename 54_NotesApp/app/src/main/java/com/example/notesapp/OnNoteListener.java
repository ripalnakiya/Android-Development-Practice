package com.example.notesapp;

import com.example.notesapp.repository.room.Note;

public interface OnNoteListener {
    void onNoteAdd(Note note);
    void onNoteDelete(Note note);
    void onNoteUpdate(Note note);
}
