package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.login.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * FirstFragmentVM
 *
 * Change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, 9/4/21: 7:52 PM
 **/
@HiltViewModel
class FirstFragmentVM @Inject constructor() : ViewModel() {
    val message = MutableLiveData<String>().apply {
        value = "Hello from 1st fragment"
    }
}
