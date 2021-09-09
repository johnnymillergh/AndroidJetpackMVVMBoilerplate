package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.login.model.NetworkUserListItem
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.login.repository.SecondFragmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    private val _userList = MutableStateFlow<List<NetworkUserListItem>>(emptyList())
    val userList get() = _userList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val refreshUserList = secondFragmentRepository.refreshUserList()
            Timber.i("Got user list, current thread: ${Thread.currentThread()}, list: $refreshUserList")
            if (!refreshUserList.isNullOrEmpty()) {
                _userList.value = refreshUserList.subList(0, 1)
            }
        }
    }
}
