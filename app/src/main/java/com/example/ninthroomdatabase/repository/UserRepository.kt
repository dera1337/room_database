package com.example.ninthroomdatabase.repository

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ninthroomdatabase.local_db.entity.User

interface UserRepository {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewUser(user: User)

    @Delete()
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM orang_ngutang")
    fun  getAllUsers(): LiveData<List<User>>
}
