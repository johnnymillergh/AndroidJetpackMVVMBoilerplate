@file:Suppress("unused")

package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.common

import android.view.View
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.disposables.Disposable
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Set on shake proof click listener
 * @receiver View the clickable view
 * @param duration Long default 1000 milliseconds
 * @param unit TimeUnit default unit is milliseconds
 * @param listener Function1<[@kotlin.ParameterName] View, Unit> the listener for executing main processing logic after debouncing click
 * @return Disposable Disposable
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, 9/5/21: 9:49 PM
 * @see <a href="https://www.jianshu.com/p/f05e561ed13c">Android Kotlin 使用 Rxjava 实现防止快速点击（防抖动） </a>
 */
@JvmOverloads
fun View.setDebounceClickListener(
    duration: Long = 1000L,
    unit: TimeUnit = TimeUnit.MILLISECONDS,
    disableClick: Boolean = true,
    listener: (view: View) -> Unit
): Disposable {
    return Observable.create { emitter: ObservableEmitter<View> ->
        setOnClickListener {
            if (!emitter.isDisposed) {
                if (disableClick && it.isClickable) {
                    it.isClickable = false
                    Timber.d("Disabled view click. Current thread: ${Thread.currentThread()}")
                }
                Timber.d("Emitter is emitting for the next event. Current thread: ${Thread.currentThread()}")
                emitter.onNext(it)
            }
        }
    }
        .debounce(duration, unit)
        .subscribe {
            Timber.d("Before executing callback listener. Current thread: ${Thread.currentThread()}")
            it.post {
                Timber.d("Executing callback listener. Current thread: ${Thread.currentThread()}")
                listener(it)
                if (disableClick && !it.isClickable) {
                    it.isClickable = true
                    Timber.d("Enabled view click. Current thread: ${Thread.currentThread()}")
                }
            }
        }
}

/**
 * Set throttle click listener
 * @receiver View the clickable view
 * @param duration Long default 1000 milliseconds
 * @param unit TimeUnit default unit is milliseconds
 * @param listener Function1<[@kotlin.ParameterName] View, Unit> the listener for executing main processing logic after throttling click
 * @return Disposable Disposable
 */
@JvmOverloads
fun View.setThrottleClickListener(
    duration: Long = 1000L,
    unit: TimeUnit = TimeUnit.MILLISECONDS,
    listener: (view: View) -> Unit
): Disposable {
    return Observable.create(ObservableOnSubscribe<View> { emitter ->
        setOnClickListener {
            if (!emitter.isDisposed) {
                Timber.d("Emitter is emitting for the next event. Current thread: ${Thread.currentThread()}")
                emitter.onNext(it)
            }
        }
    })
        .throttleFirst(duration, unit)
        .subscribe {
            Timber.d("Before executing callback listener. Current thread: ${Thread.currentThread()}")
            it.post {
                Timber.d("Executing callback listener. Current thread: ${Thread.currentThread()}")
                listener(it)
            }
        }
}
