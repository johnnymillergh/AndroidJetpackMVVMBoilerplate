package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.time.LocalDateTime

/**
 * MainActivity
 *
 * Change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, 9/4/21: 12:15 PM
 **/
@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onStart() {
        super.onStart()
        Timber.i("onStart() was called at %s", LocalDateTime.now())
    }
}