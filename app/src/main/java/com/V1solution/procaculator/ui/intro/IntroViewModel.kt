package com.V1solution.procaculator.ui.intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.V1solution.procaculator.base.BaseViewModel
import com.V1solution.procaculator.model.IntroModel
import com.travietnanh.procaculator.R

class IntroViewModel : BaseViewModel() {
    private val _listIntro = MutableLiveData<MutableList<IntroModel>>()
    val listIntro: LiveData<MutableList<IntroModel>> = _listIntro

    fun getAllData() {
        val list = mutableListOf<IntroModel>()

        list.add(IntroModel(R.drawable.img_intro_1))
        list.add(IntroModel(R.drawable.img_intro_2))
        list.add(IntroModel(R.drawable.img_intro_3))
        list.add(IntroModel(R.drawable.img_intro_4))
        _listIntro.value = list
    }
}