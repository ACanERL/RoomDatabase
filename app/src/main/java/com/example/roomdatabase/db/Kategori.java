package com.example.roomdatabase.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Kategori {
    @PrimaryKey(autoGenerate = true)
    public int kategoriid;
    @ColumnInfo(name = "kategoriadi")
    public String kategoriadi;
}
