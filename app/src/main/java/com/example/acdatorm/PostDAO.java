package com.example.acdatorm;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface PostDAO {

    @Delete
    void delete(Post post);

    @Update
    void edit(Post post);

    @Insert
    void insert(Post post);

    @Query("select * from post where id = :id")
    Usuario get(int id);

    @Query("SELECT * FROM post")
    List<Usuario> getAll();

}
