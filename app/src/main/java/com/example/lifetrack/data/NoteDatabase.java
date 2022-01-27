package com.example.lifetrack.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lifetrack.models.NoteModel;

@Database(entities = {NoteModel.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    private static volatile NoteDatabase INSTANCE;

    public abstract NoteDao noteDao();

}