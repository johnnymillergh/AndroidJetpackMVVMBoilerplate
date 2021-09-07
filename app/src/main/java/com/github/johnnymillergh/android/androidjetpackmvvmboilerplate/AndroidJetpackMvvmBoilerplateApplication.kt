package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * AndroidJetpackMvvmBoilerplateApplication
 *
 * Change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, date: 9/4/21 1:20 PM
 **/
@HiltAndroidApp
class AndroidJetpackMvvmBoilerplateApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
