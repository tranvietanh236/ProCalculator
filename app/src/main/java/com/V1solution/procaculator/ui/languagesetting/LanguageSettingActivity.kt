package com.V1solution.procaculator.ui.languagesetting

import androidx.recyclerview.widget.LinearLayoutManager
import com.V1solution.procaculator.adapter.LanguageStartAdapter
import com.V1solution.procaculator.base.BaseActivity
import com.V1solution.procaculator.model.LanguageModel
import com.V1solution.procaculator.ui.home.HomeActivity
import com.V1solution.procaculator.utils.GlobalFunction
import com.V1solution.procaculator.utils.SystemUtil
import com.travietnanh.procaculator.databinding.ActivityLanguageSettingBinding

class LanguageSettingActivity :
    BaseActivity<ActivityLanguageSettingBinding, LanguageSettingViewModel>() {
    private lateinit var languageAdapter: LanguageStartAdapter
    private var langDevice = "en"
    private var codeLang = "en"
    override fun createBinding(): ActivityLanguageSettingBinding {
        return ActivityLanguageSettingBinding.inflate(layoutInflater)
    }

    override fun setViewModel(): LanguageSettingViewModel {
        return LanguageSettingViewModel()
    }

    override fun initView() {
        super.initView()
        languageAdapter = LanguageStartAdapter(mutableListOf(),
            this@LanguageSettingActivity,
            object : LanguageStartAdapter.OnClickListener {
                override fun onClick(languageModel: LanguageModel) {
                    viewModel.setSelectedLanguage(languageModel)
                }
            })

        val linearLayoutManager = LinearLayoutManager(this)
        binding.rvLanguage.layoutManager = linearLayoutManager
        binding.rvLanguage.adapter = languageAdapter

        viewModel.languages.observe(this) { list ->
            languageAdapter.updateData(list)
        }

        viewModel.langDevice.observe(this) { langDevice ->
            this.langDevice = langDevice
        }

        viewModel.codeLang.observe(this) { codeLang ->
            this.codeLang = codeLang
        }

        viewModel.language(this)

        viewModel.selectedLanguage.observe(this) { selectedLanguage ->
            languageAdapter.setSelectedLanguage(selectedLanguage)
        }

        binding.icCheck.setOnClickListener {
            SystemUtil.saveLocale(baseContext, codeLang)
            SystemUtil.setActive(baseContext,true)
            GlobalFunction.startActivity(this@LanguageSettingActivity, HomeActivity::class.java)
            finishAffinity()
        }

        binding.icBack.setOnClickListener {
            finish()
        }
    }
}