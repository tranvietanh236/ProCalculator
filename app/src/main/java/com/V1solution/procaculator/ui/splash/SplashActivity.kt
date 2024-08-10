package com.V1solution.procaculator.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.CountDownTimer
import com.V1solution.procaculator.base.BaseActivity
import com.V1solution.procaculator.ui.intro.IntroActivity
import com.V1solution.procaculator.ui.languagesetting.LanguageSettingActivity
import com.V1solution.procaculator.ui.languagestart.LanguageStartActivity
import com.V1solution.procaculator.utils.GlobalFunction
import com.travietnanh.procaculator.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    override fun createBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun setViewModel(): SplashViewModel {
        return SplashViewModel()
    }

    override fun initView() {
        super.initView()

        if (!isTaskRoot
            && intent.hasCategory(Intent.CATEGORY_LAUNCHER)
            && intent.action != null && intent.action.equals(Intent.ACTION_MAIN)
        ) {
            finish()
            return
        }

        countDownTimer.start()
    }
    private val countDownTimer: CountDownTimer = object : CountDownTimer(5000, 5) {
        @SuppressLint("SetTextI18n")
        override fun onTick(millisUntilFinished: Long) {
            val progressValue = ((5000 - millisUntilFinished) * 100 / 5000).toInt()
            binding.tvLoading.text = "$progressValue%"
            binding.pb.progress = progressValue
        }

        override fun onFinish() {
            GlobalFunction.startActivity(this@SplashActivity, LanguageStartActivity::class.java)
            finishAffinity()
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        //super.onBackPressed()
    }
}