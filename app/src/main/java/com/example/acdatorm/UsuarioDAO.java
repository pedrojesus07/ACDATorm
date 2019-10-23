package com.example.acdatorm;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UsuarioDAO {

    @Delete
    void delete(Usuario usuario);

    @Update
    void edit(Usuario usuario);

    @Query("SELECT * FROM usuario WHERE nombre LIKE :nombre AND " +
            "apellidos LIKE :apellidos LIMIT 1")
    Usuario findByName(String nombre, String apellidos);

    @Query("select * from usuario where id = :id")
    Usuario get(int id);

    @Query("SELECT * FROM usuario")
    List<Usuario> getAll();

    @Insert
    void insert(Usuario usuario);

    @Query("SELECT * FROM usuario WHERE id IN (:ids)")
    List<Usuario> loadAllByIds(int[] ids);

}
