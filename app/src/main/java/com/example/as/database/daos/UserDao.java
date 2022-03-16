package com.example.as.database.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.as.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    long insert(User user);

    @Query("SELECT * FROM users")
    List<User> getAll();

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    User login(String username, String password);

    @Query("SELECT * FROM users WHERE username = :username")
    List<User> checkExisted(String username);

//    @Query("SELECT * FROM users WHERE username = :username")
//    List<UserWithShoes> getCarts(String username);
}
