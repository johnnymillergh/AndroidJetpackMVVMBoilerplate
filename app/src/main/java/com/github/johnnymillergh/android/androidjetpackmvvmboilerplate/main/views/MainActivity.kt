package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.databinding.ActivityMainBinding
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.login.views.LoginActivity
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
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
    private val vm: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.vm = vm
        setListener()
        serObserver()
    }

    @SuppressLint("SetTextI18n")
    private fun serObserver() {
        vm.clickMeCounter.observe(this, {
            Timber.i("clickMeCounter has changed. newValue: $it")
            textView2.text = "${vm.helloMessage.value}: ${vm.clickMeCounter.value}"
        })
    }

    private fun setListener() {
        clickMeButton.setOnClickListener {
            vm.clickMeCounter.value = vm.clickMeCounter.value?.inc()
            Timber.i("${(it as Button).text} was clicked clickMeCounter: ${vm.clickMeCounter.value}")
            this.startActivity(Intent(this, LoginActivity().javaClass))
        }
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart() was called at %s", LocalDateTime.now())
    }
}
