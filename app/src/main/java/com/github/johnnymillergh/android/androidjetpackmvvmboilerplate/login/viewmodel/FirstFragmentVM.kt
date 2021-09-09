package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * FirstFragmentVM
 *
 * Change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, 9/4/21: 7:52 PM
 **/
@FlowPreview
@HiltViewModel
class FirstFragmentVM @Inject constructor() : ViewModel() {
    val message = MutableStateFlow("Hello from 1st fragment")
    val message2 = MutableStateFlow("Hello from 1st fragment 2")

    init {
        viewModelScope.launch {
            message.debounce(1000).collect {
                message2.emit(it)
            }
        }
        viewModelScope.launch {
            message2.collect {
                Timber.i("message2 has changed: $it")
            }
        }
    }
}
