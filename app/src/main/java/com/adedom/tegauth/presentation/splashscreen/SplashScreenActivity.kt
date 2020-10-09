package com.adedom.tegauth.presentation.splashscreen

import android.content.Intent
import android.os.Bundle
import com.adedom.library.presentation.splashscreen.SplashScreenViewModel
import com.adedom.tegauth.R
import com.adedom.tegauth.base.BaseActivity
import com.adedom.tegauth.presentation.main.MainActivity
import com.adedom.tegauth.presentation.signin.SignInActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreenActivity : BaseActivity() {

    val viewModel by viewModel<SplashScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        viewModel.attachFirstTime.observe {
            if (viewModel.initialize()) {
                Intent(baseContext, MainActivity::class.java).apply {
                    finish()
                    startActivity(this)
                }
            } else {
                Intent(baseContext, SignInActivity::class.java).apply {
                    finish()
                    startActivity(this)
                }
            }
        }

        viewModel.error.observeError()

    }

}
