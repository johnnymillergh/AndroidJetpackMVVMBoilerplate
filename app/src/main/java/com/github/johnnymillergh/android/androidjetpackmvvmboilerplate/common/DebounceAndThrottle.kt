package com.github.johnnymillergh.android.androidjetpackmvvmboilerplate.common

import android.view.View
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.disposables.Disposable
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Set on shake proof click listener
 * @receiver View
 * @param duration Long
 * @param unit TimeUnit
 * @param listener Function1<[@kotlin.ParameterName] View, Unit>
 * @return Disposable
 * @author Johnny Miller (锺俊), email: johnnysviva@outlook.com, 9/5/21: 9:49 PM
 * @see <a href="https://www.jianshu.com/p/f05e561ed13c">Android Kotlin 使用 Rxjava 实现防止快速点击（防抖动） </a>
 */
@JvmOverloads
fun View.setDebounceClickListener(
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
        .debounce(duration, unit)
        .subscribe {
            it.post {
                Timber.d("Executing callback listener. Current thread: ${Thread.currentThread()}")
                listener(it)
            }
        }
}

/**
 * Set throttle click listener
 * @receiver View
 * @param duration Long
 * @param unit TimeUnit
 * @param listener Function1<[@kotlin.ParameterName] View, Unit>
 * @return Disposable
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
            it.post {
                Timber.d("Executing callback listener. Current thread: ${Thread.currentThread()}")
                listener(it)
            }
        }
}
