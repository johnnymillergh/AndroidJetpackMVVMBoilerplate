package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.repository

import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.dao.UserVisitRecordDao
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.model.UserVisitRecord
import timber.log.Timber
import javax.inject.Inject

/**
 * Description: MainActivityRepository, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 9/6/2021 6:08 PM
 **/
class MainActivityRepository @Inject constructor(
    private val userVisitRecordDao: UserVisitRecordDao
) {
    suspend fun saveUserVisitRecord(userVisitRecord: UserVisitRecord) {
        userVisitRecordDao.insert(userVisitRecord)
        Timber.i("Saved user visit record into database. Current thread: ${Thread.currentThread()}")
    }

    suspend fun getTheLatestUserVisitRecord(): UserVisitRecord {
        return userVisitRecordDao.selectTheLatest()
    }
}
