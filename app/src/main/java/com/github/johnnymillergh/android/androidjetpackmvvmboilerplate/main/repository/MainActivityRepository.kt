package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.repository;

import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.common.configuration.database.DemoSQLiteDatabase
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.model.UserVisitRecord
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Description: MainActivityRepository, change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 9/6/2021 6:08 PM
 **/
class MainActivityRepository @Inject constructor(
    private val demoSQLiteDatabase: DemoSQLiteDatabase
) {
    fun saveUserVisitRecord(userVisitRecord: UserVisitRecord) {
        Observable.create { emitter: ObservableEmitter<UserVisitRecord> ->
            Timber.i("Asynchronously saving user visited data. Current thread: ${Thread.currentThread()}")
            demoSQLiteDatabase.userVisitRecordDao.insert(userVisitRecord)
            emitter.onNext(userVisitRecord)
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                Timber.i("Saved user visit record into database: $it. Current thread: ${Thread.currentThread()}")
            }
    }
}
