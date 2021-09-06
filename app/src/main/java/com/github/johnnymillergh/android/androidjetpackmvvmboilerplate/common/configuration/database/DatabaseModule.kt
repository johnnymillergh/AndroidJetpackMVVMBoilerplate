package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.common.configuration.database

import android.content.Context
import androidx.room.Room
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.dao.UserVisitRecordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * DatabaseModule
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 9/6/2021 3:36 PM
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDemoSQLiteDatabase(@ApplicationContext appContext: Context): DemoSQLiteDatabase {
        return Room
            .databaseBuilder(
                appContext,
                DemoSQLiteDatabase::class.java,
                "DemoSQLite.db"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideChannelDao(demoSQLiteDatabase: DemoSQLiteDatabase): UserVisitRecordDao {
        return demoSQLiteDatabase.userVisitRecordDao
    }
}
