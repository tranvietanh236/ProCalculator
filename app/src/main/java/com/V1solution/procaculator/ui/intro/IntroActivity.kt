package com.V1solution.procaculator.ui.intro

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.V1solution.procaculator.adapter.IntroAdapter
import com.V1solution.procaculator.base.BaseActivity
import com.V1solution.procaculator.ui.permission.PermissionActivity
import com.V1solution.procaculator.utils.GlobalFunction
import com.travietnanh.procaculator.R
import com.travietnanh.procaculator.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity<ActivityIntroBinding, IntroViewModel>() {
    private var introAdapter: IntroAdapter? = null
    override fun createBinding(): ActivityIntroBinding {
        return ActivityIntroBinding.inflate(layoutInflater)
    }

    override fun setViewModel(): IntroViewModel {
        return IntroViewModel()
    }

    override fun initView() {
        super.initView()
        introAdapter = IntroAdapter(this@IntroActivity)
        binding.vpIntro.adapter = introAdapter
        binding.vpIntro.offscreenPageLimit = 3
        binding.dotsindicator.attachTo(binding.vpIntro)
        binding.vpIntro.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER


        binding.vpIntro.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        binding.icNext.setImageResource(R.drawable.ic_next)
                        binding.adsContainer.visibility = View.VISIBLE
                        binding.tvTitle.text = getString(R.string.simple_calculator_for_daily_use)
                        binding.tvContent.text =
                            getString(R.string.our_calculator_app_offers_basic_functions)
                    }

                    1 -> {
                        binding.icNext.setImageResource(R.drawable.ic_next)
                        binding.adsContainer.visibility = View.GONE
                        binding.tvTitle.text = getString(R.string.accurate_calculator_for_study)
                        binding.tvContent.text =
                            getString(R.string.whether_you_re_a_student_or_teacher_our_calculator_app_meets_all_your_calculation_needs_with_robust_features_and_reliable_performance)
                    }

                    2 -> {
                        binding.icNext.setImageResource(R.drawable.ic_next)
                        binding.adsContainer.visibility = View.GONE
                        binding.tvTitle.text = getString(R.string.customize_formulas)
                        binding.tvContent.text =
                            getString(R.string.customize_formulas_in_this_calculator_app_for_quick_precise_results_perfect_for_students_and_professionals_alike)
                    }

                    3 -> {
                        binding.icNext.setImageResource(R.drawable.ic_next_2)
                        binding.adsContainer.visibility = View.VISIBLE
                        binding.tvTitle.text = getString(R.string.simplify_your_calculationss)
                        binding.tvContent.text =
                            getString(R.string.instant_access_to_essential_math_formulas)
                    }
                }
            }
        })

        binding.icNext.setOnClickListener {
            val currentItem = binding.vpIntro.currentItem
            binding.vpIntro.setCurrentItem(currentItem + 1, true)
            if (currentItem == 3){
                GlobalFunction.startActivity(this@IntroActivity, PermissionActivity::class.java)
                finishAffinity()
            }
        }
    }

    override fun viewModel() {
        super.viewModel()
        viewModel.getAllData()
        viewModel.listIntro.observe(this) { list ->
            introAdapter?.list?.clear()
            introAdapter?.list?.addAll(list)
            introAdapter?.notifyDataSetChanged()
        }
    }
}