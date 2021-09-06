package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.login.service

import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.login.model.NetworkUserListItem
import retrofit2.http.GET

/**
 * DemoService
 *
 * Change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, 9/6/21: 8:47 PM
 **/
interface DemoService {
    @GET("/repos/square/retrofit/stargazers")
    suspend fun getUserList(): List<NetworkUserListItem>
}
