package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.view

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.common.setDebounceClickListener
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.databinding.ActivityMainBinding
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.login.view.LoginActivity
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.viewmodel.MainActivityVM
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
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
        lifecycleScope.launchWhenStarted {
            Timber.i("lifecycleScope launched a coroutine. Current thread: ${Thread.currentThread()}, $this")
            vm.clickMeCounter.collect {
                val message = "clickMeCounter has changed. newValue: $it"
                Timber.i(message)
                Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
                textView2.text = vm.concatMessage()
            }
        }
        lifecycleScope.launchWhenStarted {
            vm.userVisitRecord.collect {
                userVisitRecordTextView.text = it.toString()
            }
        }
    }

    private fun setListener() {
        clickMeButton.setDebounceClickListener(250L) {
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
