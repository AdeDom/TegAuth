package com.adedom.tegauth.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.adedom.tegauth.util.extension.snackbar

abstract class BaseActivity : AppCompatActivity() {

    protected inline fun <reified T> LiveData<T>.observe(crossinline onNext: (T) -> Unit) {
        observe(this@BaseActivity, { onNext(it) })
    }

    protected fun LiveData<Throwable>.observeError(view: View) {
        observe(this@BaseActivity, {
            it.printStackTrace()
            view.snackbar("Error: ${it.message}")
        })
    }

}
