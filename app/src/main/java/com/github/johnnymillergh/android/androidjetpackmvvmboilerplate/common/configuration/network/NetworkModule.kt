package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.common.configuration.network

import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.BuildConfig
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.login.service.DemoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * NetworkModule
 *
 * Change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, 9/6/21: 8:47 PM
 **/
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://api.github.com")
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideDemoService(retrofit: Retrofit): DemoService =
        retrofit.create(DemoService::class.java)
}
