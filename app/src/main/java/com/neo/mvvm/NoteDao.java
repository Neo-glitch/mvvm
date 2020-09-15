package com.neo.mvvm;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * DataAccessObject Interface to comm with SQLite db(kinda like AppProvider)
 */
@Dao
public interface NoteDao{

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    // @Query is used to define the custom db opp that we want to do
    @Query("delete from note_table")
    void deleteAllNotes();

    @Query("select * from note_table order by priority desc")
    LiveData<List<Note>> getAllNotes();


}
