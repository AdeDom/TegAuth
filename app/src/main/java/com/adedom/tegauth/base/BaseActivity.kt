package com.adedom.tegauth.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.adedom.tegauth.util.extension.toast

abstract class BaseActivity : AppCompatActivity() {

    protected inline fun <reified T> LiveData<T>.observe(crossinline onNext: (T) -> Unit) {
        observe(this@BaseActivity, { onNext(it) })
    }

    protected fun LiveData<Throwable>.observeError() {
        observe(this@BaseActivity, {
            it.printStackTrace()
            toast(it.message)
        })
    }

}
