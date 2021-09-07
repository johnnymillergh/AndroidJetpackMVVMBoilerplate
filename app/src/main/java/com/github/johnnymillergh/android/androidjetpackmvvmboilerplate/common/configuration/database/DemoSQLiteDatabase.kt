package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.common.configuration.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.BuildConfig
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.dao.UserVisitRecordDao
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.model.UserVisitRecord

/**
 * Description: DemoSQLiteDatabase, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 9/6/2021 3:53 PM
 **/
@Database(
    entities = [
        UserVisitRecord::class
    ],
    version = BuildConfig.DATABASE_VERSION_CODE
)
@TypeConverters(
    value = [
        LocalDateTimeConverters::class
    ]
)
abstract class DemoSQLiteDatabase : RoomDatabase() {
    abstract val userVisitRecordDao: UserVisitRecordDao
}
