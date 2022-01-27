package com.example.lifetrack.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "note_table")
public class NoteModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "task_name")
    public String taskName;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "frequency")
    public String frequency;


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
