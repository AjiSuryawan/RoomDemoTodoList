package com.ashish.roomdemo.database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.ashish.roomdemo.model.NoteModel;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM NoteModel ORDER BY ID")
    List<NoteModel> loadAllPersons();

    @Insert
    void insertNote(NoteModel noteModel);

    @Update
    void updateNote(NoteModel noteModel);

    @Delete
    void delete(NoteModel noteModel);

    @Query("SELECT * FROM NoteModel WHERE id = :id")
    NoteModel loadNoteById(int id);
}
