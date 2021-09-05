package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * MainViewModel
 *
 * Change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, 9/4/21: 9:41 PM
 **/
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    val clickMeCounter = MutableLiveData(0)
    val helloMessage = MutableLiveData("Hello world!")
}
