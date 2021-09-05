package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.databinding.ActivityMainBinding
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.login.views.LoginActivity
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.time.LocalDateTime

/**
 * MainActivity
 *
 * Change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, 9/4/21: 12:15 PM
 **/
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.activity = this
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart() was called at %s", LocalDateTime.now())
    }

    fun handleClickMeButton(view: View) {
        Timber.i("${view.display.name} was click")
        this.startActivity(Intent(this, LoginActivity().javaClass))
    }
}