package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.view

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.common.setDebounceClickListener
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.databinding.ActivityMainBinding
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.login.view.LoginActivity
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.viewmodel.MainActivityVM
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
    private val vm: MainActivityVM by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.vm = vm
        setListener()
        serObserver()
        vm.saveUserVisitRecord()
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        Timber.i("onPostCreate")
    }

    private fun serObserver() {
        vm.clickMeCounter.observe(this, {
            Timber.i("clickMeCounter has changed. newValue: $it")
            textView2.text = vm.concatMessage()
        })
    }

    private fun setListener() {
        clickMeButton.setDebounceClickListener {
            vm.increaseClickMeCounter()
            Timber.i("${(it as Button).text} was clicked clickMeCounter: ${vm.clickMeCounter.value}")
            this.startActivity(Intent(this, LoginActivity().javaClass))
        }
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart() was called at %s", LocalDateTime.now())
    }
}
