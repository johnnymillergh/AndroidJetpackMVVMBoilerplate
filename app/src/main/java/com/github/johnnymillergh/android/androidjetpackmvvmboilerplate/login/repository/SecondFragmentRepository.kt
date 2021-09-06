package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.login.repository

import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.login.model.NetworkUserListItem
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.login.service.DemoService
import timber.log.Timber
import javax.inject.Inject

/**
 * SecondFragmentRepository
 *
 * Change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, 9/6/21: 8:50 PM
 **/
class SecondFragmentRepository @Inject constructor(
    private val demoService: DemoService
) {
    suspend fun refreshUserList(): List<NetworkUserListItem> {
        return try {
            demoService.getUserList()
        } catch (e: Exception) {
            Timber.e(
                e,
                "Exception occurred when getting user list! Exception message: ${e.message}"
            )
            emptyList()
        }
    }
}
