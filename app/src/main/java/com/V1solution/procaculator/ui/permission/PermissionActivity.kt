package com.V1solution.procaculator.ui.permission

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat
import com.V1solution.procaculator.base.BaseActivity
import com.V1solution.procaculator.ui.home.HomeActivity
import com.V1solution.procaculator.utils.GlobalFunction
import com.travietnanh.procaculator.R
import com.travietnanh.procaculator.databinding.ActivityPermissionBinding

class PermissionActivity : BaseActivity<ActivityPermissionBinding, PermissionViewModel>() {
    private var isDeviceStorage = false
    private var isScreenOverlay = false
    private var isAccessiBility = false
    override fun createBinding(): ActivityPermissionBinding {
        return ActivityPermissionBinding.inflate(layoutInflater)
    }

    override fun setViewModel(): PermissionViewModel {
        return PermissionViewModel()
    }

    override fun initView() {
        super.initView()
        binding.clAccessibilityService.setOnClickListener {
            if (!isAccessiBility) {
                binding.icAccessibilityService.setImageResource(R.drawable.ic_permission_on)
            }
            else{
                binding.icAccessibilityService.setImageResource(R.drawable.ic_permission_off)
            }
            isAccessiBility = !isAccessiBility
        }
        binding.clScreenOverlayAccess.setOnClickListener {
            if (!isScreenOverlay) {
                binding.icScreenOverlayAccess.setImageResource(R.drawable.ic_permission_on)
            }
            else{
                binding.icScreenOverlayAccess.setImageResource(R.drawable.ic_permission_off)
            }
            isScreenOverlay = !isScreenOverlay

        }

        binding.clDeviceStorageAccess.setOnClickListener {
            if (!isDeviceStorage) {
                binding.icDeviceStorageAccess.setImageResource(R.drawable.ic_permission_on)
            }
            else{
                binding.icDeviceStorageAccess.setImageResource(R.drawable.ic_permission_off)
            }
            isDeviceStorage = !isDeviceStorage
        }

        binding.tvSaveContinue.setOnClickListener {
            GlobalFunction.startActivity(this@PermissionActivity, HomeActivity::class.java)
            finishAffinity()
        }
    }

}