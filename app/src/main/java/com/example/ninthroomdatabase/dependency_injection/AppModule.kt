package com.example.ninthroomdatabase.dependency_injection

import android.content.Context
import androidx.room.Room
import com.example.ninthroomdatabase.local_db.UserDAO
import com.example.ninthroomdatabase.local_db.UserDatabase
import com.example.ninthroomdatabase.repository.UserRepository
import com.example.ninthroomdatabase.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserDatabase(
        @ApplicationContext context: Context
    ): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "testing_database"
        ).build()
    }
    @Provides
    @Singleton
    fun provideUserDAO(db: UserDatabase): UserDAO {
        return db.getDAO()
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        dao: UserDAO
    ): UserRepository {
        return UserRepositoryImpl(dao)
    }
}