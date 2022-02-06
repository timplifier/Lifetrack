package com.example.lifetrack.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class NoteModel {
    @PrimaryKey(autoGenerate = true)
    public int id;

    private String taskName;
    private String date;
    private String frequency;




    public NoteModel(String taskName, String date, String frequency) {
        this.taskName = taskName;
        this.date = date;
        this.frequency = frequency;
    }

    public NoteModel() {

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
