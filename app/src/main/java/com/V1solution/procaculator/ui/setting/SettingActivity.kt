package com.V1solution.procaculator.ui.setting

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.V1solution.procaculator.base.BaseActivity
import com.V1solution.procaculator.rating.RatingDialog
import com.V1solution.procaculator.ui.languagesetting.LanguageSettingActivity
import com.V1solution.procaculator.utils.GlobalFunction
import com.V1solution.procaculator.utils.SharePrefUtils
import com.google.android.gms.tasks.Task
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManagerFactory
import com.travietnanh.procaculator.R
import com.travietnanh.procaculator.databinding.ActivitySettingBinding

class SettingActivity : BaseActivity<ActivitySettingBinding, SettingViewModel>(),
    View.OnClickListener {
    private var check = false
    private val launcherNew =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        }

    override fun createBinding(): ActivitySettingBinding {
        return ActivitySettingBinding.inflate(layoutInflater)
    }

    override fun setViewModel(): SettingViewModel {
        return SettingViewModel()
    }

    override fun initView() {
        super.initView()
        binding.icBack.setOnClickListener(this)
        binding.clLanguage.setOnClickListener(this)
        binding.clShare.setOnClickListener(this)
        binding.clRating.setOnClickListener(this)
        binding.clPolicy.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.ic_back -> {
                finish()
            }

            R.id.cl_language -> {
                GlobalFunction.startActivity(
                    this@SettingActivity,
                    LanguageSettingActivity::class.java
                )
            }

            R.id.cl_share -> {
                if (!check) {
                    check = true
                    val intentShare = Intent(Intent.ACTION_SEND)
                    intentShare.type = "text/plain"
                    intentShare.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
                    intentShare.putExtra(
                        Intent.EXTRA_TEXT,
                        """${getString(R.string.app_name)}https://play.google.com/store/apps/details?id=${this.packageName}""".trimIndent()
                    )
                    startActivity(Intent.createChooser(intentShare, "Share"))
                }
            }

            R.id.cl_rating -> {
                if (!check) {
                    showRateDialogNew()
                }
            }

            R.id.cl_policy -> {
                if (!check) {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://firebasestorage.googleapis.com/v0/b/asa167-water-eject-f9b64.appspot.com/o/Privacy-Policy.html?alt=media&token=6cdfa047-f282-4329-af67-0aff0ad599bb")
                    )
                    launcherNew.launch(intent)
                }

            }

        }
    }

    private fun showRateDialogNew() {
        check = true
        val manager = ReviewManagerFactory.create(this@SettingActivity)
        val ratingDialog = RatingDialog(this@SettingActivity)
        ratingDialog.init(this@SettingActivity, object : RatingDialog.OnPressListener {
            override fun send(s: Int) {
                SharePrefUtils.forceRated(this@SettingActivity)
                GlobalFunction.showToastMessage(this@SettingActivity, R.string.Thank_You.toString())
            }

            override fun rating(s: Int) {
                SharePrefUtils.forceRated(this@SettingActivity)
                val request = manager.requestReviewFlow()
                request.addOnCompleteListener { task: Task<ReviewInfo?> ->
                    if (task.isSuccessful) {
                        SharePrefUtils.forceRated(this@SettingActivity)
                    }
                }
            }

            override fun cancel() {
                ratingDialog.dismiss()
            }

            override fun later() {
                ratingDialog.dismiss()
            }

            override fun gotit() {
                ratingDialog.dismiss()
            }

        })
        ratingDialog.show()
        ratingDialog.setOnDismissListener {
            check = false
            if (SharePrefUtils.isRated(this@SettingActivity)) {
                binding.clRating.visibility = View.GONE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        check = false
    }
}