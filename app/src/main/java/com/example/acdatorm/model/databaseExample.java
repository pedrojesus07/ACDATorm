package com.example.acdatorm.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.acdatorm.Post;
import com.example.acdatorm.PostDAO;
import com.example.acdatorm.Usuario;
import com.example.acdatorm.UsuarioDAO;

@Database(entities = {Usuario.class}, version = 1,exportSchema = false)
public abstract class databaseExample extends RoomDatabase {
    public abstract UsuarioDAO usuarioDao();
    //public abstract PostDAO postDAO();
}
