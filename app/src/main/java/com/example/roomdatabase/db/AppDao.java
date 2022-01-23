package com.example.roomdatabase.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AppDao {
@Query("Select*From Kategori")
    List<Kategori>getAllKategori();
@Insert
    void insertKategori(Kategori...kategori);
@Update
    void  updateKategori(Kategori...kategori);
@Delete
    void deleteKategori(Kategori...kategori);
}
