package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.viewmodel

import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.model.UserVisitRecord
import com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.main.repository.MainActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDateTime
import javax.inject.Inject

/**
 * MainActivityVM
 *
 * Change description here.
 *
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, 9/4/21: 9:41 PM
 **/
@HiltViewModel
class MainActivityVM @Inject constructor(
    private val mainActivityRepository: MainActivityRepository
) : ViewModel() {
    /**
     * LiveData -> MutableStateFlow
     * @see <a href='https://johnoreilly.dev/posts/jetpack-compose-stateflow-livedata/'>Comparing use of LiveData and StateFlow in a Jetpack Compose project</a>
     */
    val clickMeCounter = MutableStateFlow(0)
    private val helloMessage = MutableStateFlow("Hello world!")
    private val _userVisitRecord = MutableStateFlow(UserVisitRecord(0, "", LocalDateTime.MIN, ""))
    val userVisitRecord get() = _userVisitRecord.asStateFlow()

    init {
        getTheLatestUserVisitRecord()
    }

    fun increaseClickMeCounter() {
        Timber.i("Increase click me counter (MutableLiveData): $clickMeCounter")
        clickMeCounter.value = clickMeCounter.value.inc()
    }

    fun concatMessage(): CharSequence {
        return "${helloMessage.value}: ${clickMeCounter.value}"
    }

    fun saveUserVisitRecord() = viewModelScope.launch {
        mainActivityRepository.saveUserVisitRecord(
            UserVisitRecord(
                null,
                Build.USER,
                LocalDateTime.now(),
                Build.VERSION.RELEASE
            )
        )
    }

    private fun getTheLatestUserVisitRecord() = viewModelScope.launch {
        _userVisitRecord.value = mainActivityRepository.getTheLatestUserVisitRecord()
    }
}
