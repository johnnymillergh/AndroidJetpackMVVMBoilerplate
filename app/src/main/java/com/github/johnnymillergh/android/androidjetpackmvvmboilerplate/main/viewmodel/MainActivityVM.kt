package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.viewmodel

import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.model.UserVisitRecord
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.repository.MainActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import java.time.LocalDateTime
import javax.inject.Inject

/**
 * MainActivityVM
 *
 * Change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, 9/4/21: 9:41 PM
 **/
@HiltViewModel
class MainActivityVM @Inject constructor(
    private val mainActivityRepository: MainActivityRepository
) : ViewModel() {
    val clickMeCounter = MutableLiveData(0)
    private val helloMessage = MutableLiveData("Hello world!")

    fun increaseClickMeCounter() {
        Timber.i("Increase click me counter (MutableLiveData): $clickMeCounter")
        clickMeCounter.value = clickMeCounter.value?.inc()
    }

    fun concatMessage(): CharSequence {
        return "${helloMessage.value}: ${clickMeCounter.value}"
    }

    fun saveUserVisitRecord() {
        mainActivityRepository.saveUserVisitRecord(
            UserVisitRecord(
                null,
                Build.USER,
                LocalDateTime.now(),
                Build.VERSION.RELEASE
            )
        )
    }
}
