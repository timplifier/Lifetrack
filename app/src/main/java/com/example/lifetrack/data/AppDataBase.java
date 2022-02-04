package com.example.lifetrack.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lifetrack.models.NoteModel;

@Database(entities = {NoteModel.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
