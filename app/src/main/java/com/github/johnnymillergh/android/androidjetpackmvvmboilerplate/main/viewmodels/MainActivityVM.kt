package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

/**
 * MainActivityVM
 *
 * Change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, 9/4/21: 9:41 PM
 **/
@HiltViewModel
class MainActivityVM @Inject constructor() : ViewModel() {
    val clickMeCounter = MutableLiveData(0)
    private val helloMessage = MutableLiveData("Hello world!")

    fun increaseClickMeCounter() {
        Timber.i("Increase click me counter (MutableLiveData): $clickMeCounter")
        clickMeCounter.value = clickMeCounter.value?.inc()
    }

    fun concatMessage(): CharSequence {
        return "${helloMessage.value}: ${clickMeCounter.value}"
    }
}
