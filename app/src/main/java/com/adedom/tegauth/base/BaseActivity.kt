package com.adedom.tegauth.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.adedom.library.domain.Resource
import com.adedom.tegauth.presentation.splashscreen.SplashScreenActivity
import com.adedom.tegauth.util.extension.toast

abstract class BaseActivity : AppCompatActivity() {

    protected inline fun <reified T> LiveData<T>.observe(crossinline onNext: (T) -> Unit) {
        observe(this@BaseActivity, { onNext(it) })
    }

    protected fun LiveData<Resource.Error>.observeError() {
        observe(this@BaseActivity, {
            if (it.tokenExpire) {
                Intent(baseContext, SplashScreenActivity::class.java).apply {
                    finishAffinity()
                    startActivity(this)
                }
            } else {
                it.throwable.printStackTrace()
                toast(it.throwable.message)
            }
        })
    }

}
