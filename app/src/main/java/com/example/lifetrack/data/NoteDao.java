package com.example.lifetrack.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.lifetrack.models.NoteModel;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insert(NoteModel noteModel);

    @Delete
    void delete(NoteModel noteModel);

    @Query("SELECT * FROM note_table")
    LiveData<List<NoteModel>> getData();

    @Query("DELETE FROM note_table")
    void deleteAll();
}
