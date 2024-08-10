package com.V1solution.procaculator.ui.home

import android.view.View
import com.V1solution.procaculator.adapter.LanguageStartAdapter
import com.V1solution.procaculator.base.BaseActivity
import com.V1solution.procaculator.ui.setting.SettingActivity
import com.V1solution.procaculator.utils.GlobalFunction
import com.travietnanh.procaculator.R
import com.travietnanh.procaculator.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {
    override fun createBinding(): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun setViewModel(): HomeViewModel {
        return HomeViewModel()
    }

    override fun initView() {
        super.initView()
        binding.icSetting.setOnClickListener{
            GlobalFunction.startActivity(this@HomeActivity, SettingActivity::class.java)
        }
    }


}