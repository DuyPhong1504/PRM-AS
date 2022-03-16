package com.example.as.database.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.as.model.ShoeV2;

@Dao
public interface ShoeV2Dao {

    @Insert
    long insert(ShoeV2 shoe);

    @Query("SELECT * FROM shoes WHERE id = :id")
    ShoeV2 getById(int id);

    @Delete
    void delete(ShoeV2 shoe);

    @Update
    void update(ShoeV2 shoe);
}
