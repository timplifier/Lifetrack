package com.example.lifetrack.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class NoteModel {
    private final String taskName;
    private final String date;
    private final String frequency;
    @PrimaryKey(autoGenerate = true)
    public int id;

    public NoteModel(String taskName, String date, String frequency) {
        this.taskName = taskName;
        this.date = date;
        this.frequency = frequency;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDate() {
        return date;
    }

    public String getFrequency() {
        return frequency;
    }
}
