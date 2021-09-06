package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.login.model.NetworkUserListItem
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.login.repository.SecondFragmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * SecondFragmentVM
 *
 * Change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, 9/6/21: 9:03 PM
 **/
@HiltViewModel
class SecondFragmentVM @Inject constructor(
    private val secondFragmentRepository: SecondFragmentRepository
) : ViewModel() {
    private val _userList =
        MutableLiveData<List<NetworkUserListItem>>(emptyList())
    val userList: LiveData<List<NetworkUserListItem>> get() = _userList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val refreshUserList = secondFragmentRepository.refreshUserList()
            _userList.postValue(refreshUserList.subList(0, 1))
            Timber.i("Got user list, current thread: ${Thread.currentThread()}, list: $refreshUserList")
        }
    }
}
