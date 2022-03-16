package com.example.as.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.as.database.daos.CartDao;
import com.example.as.database.daos.ShoeV2Dao;
import com.example.as.database.daos.UserDao;
import com.example.as.model.Cart;
import com.example.as.model.ShoeV2;
import com.example.as.model.User;

@Database(entities = {User.class, ShoeV2.class, Cart.class}, version = 6)
public abstract class EntityDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "prm.db";
    private static EntityDatabase instance;

    public static synchronized EntityDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), EntityDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract UserDao getUserDao();
    public abstract ShoeV2Dao getShoeV2Dao();
    public abstract CartDao getCartDao();
}
