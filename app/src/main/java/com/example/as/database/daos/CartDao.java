package com.example.as.database.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.as.model.Cart;

import java.util.List;

@Dao
public interface CartDao {
    @Insert
    long insert(Cart cart);

    @Insert
    long[] insertAll(Cart... cart);
    @Update
    void update(Cart cart);

    @Update
    void updateAll(Cart... cart);

}
