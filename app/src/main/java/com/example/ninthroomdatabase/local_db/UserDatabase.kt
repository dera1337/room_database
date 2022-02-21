package com.example.ninthroomdatabase.local_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ninthroomdatabase.local_db.entity.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase(){

    abstract fun getDAO(): UserDAO
}