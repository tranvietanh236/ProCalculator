package com.V1solution.procaculator.ui.languagestart

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.V1solution.procaculator.adapter.LanguageStartAdapter
import com.V1solution.procaculator.base.BaseActivity
import com.V1solution.procaculator.model.LanguageModel
import com.V1solution.procaculator.ui.intro.IntroActivity
import com.V1solution.procaculator.utils.GlobalFunction
import com.V1solution.procaculator.utils.SystemUtil
import com.travietnanh.procaculator.databinding.ActivityLanguageStartBinding

class LanguageStartActivity : BaseActivity<ActivityLanguageStartBinding, LanguageViewModel>() {
    private lateinit var languageAdapter: LanguageStartAdapter
    private var langDevice = "en"
    private var codeLang = "en"
    override fun createBinding(): ActivityLanguageStartBinding {
        return ActivityLanguageStartBinding.inflate(layoutInflater)
    }

    override fun setViewModel(): LanguageViewModel {
        return LanguageViewModel()
    }

    override fun initView() {
        super.initView()
        languageAdapter = LanguageStartAdapter(mutableListOf(),
            this@LanguageStartActivity,
            object : LanguageStartAdapter.OnClickListener{
                override fun onClick(languageModel: LanguageModel) {
                    viewModel.setSelectedLanguage(languageModel)
                    binding.icCheck.visibility = View.VISIBLE
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

        viewModel.languageStart(this)

        viewModel.selectedLanguage.observe(this) { selectedLanguage ->
            languageAdapter.setSelectedLanguage(selectedLanguage)
        }
        binding.icCheck.setOnClickListener {
            SystemUtil.saveLocale(baseContext, codeLang)
            SystemUtil.setActive(baseContext,true)
            GlobalFunction.startActivity(this@LanguageStartActivity, IntroActivity::class.java)
            finishAffinity()
        }
    }
}