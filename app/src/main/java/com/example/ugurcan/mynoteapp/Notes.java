package com.example.ugurcan.mynoteapp;

import java.io.Serializable;
import java.util.Date;

public class Notes implements Serializable {
    private String noteId;
    private String noteTitle;
    private String note;
    private String noteDate;

    public Notes() {
    }

    public Notes(String noteId, String noteTitle, String note, String noteDate) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.note = note;
        this.noteDate = noteDate;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }
}
