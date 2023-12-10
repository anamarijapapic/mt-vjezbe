package org.oss.lab3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM note")
    List<Note> getAll();

    @Query("SELECT * FROM note WHERE uid IN (:noteIds)")
    List<Note> loadAllByIds(int[] noteIds);

    @Insert
    void insertAll(Note... notes);

    @Delete
    void delete(Note note);

    @Query("UPDATE note SET title = :title, content = :content WHERE uid = :uid")
    void update(int uid, String title, String content);

    @Query("DELETE FROM note WHERE uid = :uid")
    void deleteById(int uid);
}
