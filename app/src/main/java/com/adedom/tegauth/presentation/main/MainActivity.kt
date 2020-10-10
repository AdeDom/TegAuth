package com.adedom.tegauth.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.adedom.library.presentation.main.MainViewModel
import com.adedom.teg.util.TegConstant
import com.adedom.tegauth.R
import com.adedom.tegauth.base.BaseActivity
import com.adedom.tegauth.presentation.changepassword.ChangePasswordActivity
import com.adedom.tegauth.presentation.changeprofile.ChangeProfileActivity
import com.adedom.tegauth.presentation.splashscreen.SplashScreenActivity
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
                val text = "${state.playerInfo?.name} : ${state.playerInfo?.level}"

                materialTextView.text = text
            }
        }

        viewModel.error.observeError()

        viewModel.fetchPlayerInfo()

        btSignOut.setOnClickListener {
            viewModel.signOut()
            Intent(baseContext, SplashScreenActivity::class.java).apply {
                finishAffinity()
                startActivity(this)
            }
        }

        btChangePassword.setOnClickListener {
            Intent(baseContext, ChangePasswordActivity::class.java).apply {
                startActivity(this)
            }
        }

        btChangeProfile.setOnClickListener {
            Intent(baseContext, ChangeProfileActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.callPlayerState(TegConstant.STATE_ONLINE)
    }

    override fun onPause() {
        super.onPause()
        viewModel.callPlayerState(TegConstant.STATE_OFFLINE)
    }

}
