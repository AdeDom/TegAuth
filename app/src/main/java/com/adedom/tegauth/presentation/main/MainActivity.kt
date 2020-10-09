package com.adedom.tegauth.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.adedom.library.presentation.main.MainViewModel
import com.adedom.tegauth.R
import com.adedom.tegauth.base.BaseActivity
import com.adedom.tegauth.presentation.splashscreen.SplashScreenActivity
import com.adedom.tegauth.util.extension.toast
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.state.observeForever { state ->
            progressBar.visibility = if (state.loading) View.VISIBLE else View.INVISIBLE

            if (state.playerInfo != null) {
                toast(state.playerInfo?.name)
            }
        }

        viewModel.attachFirstTime.observe {
            viewModel.initialize()
        }

        viewModel.error.observeError()

        btSignOut.setOnClickListener {
            viewModel.signOut()
            Intent(baseContext, SplashScreenActivity::class.java).apply {
                finishAffinity()
                startActivity(this)
            }
        }
    }

}
