package com.V1solution.procaculator.ui.languagestart

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.V1solution.procaculator.base.BaseViewModel
import com.V1solution.procaculator.model.LanguageModel
import com.travietnanh.procaculator.R

class LanguageViewModel : BaseViewModel(){
    val languages = MutableLiveData<List<LanguageModel>>()
    val selectedLanguage = MutableLiveData<LanguageModel>()

    private val _langDevice = MutableLiveData<String>()
    val langDevice: LiveData<String>
        get() = _langDevice

    private val _codeLang = MutableLiveData<String>()
    val codeLang: LiveData<String>
        get() = _codeLang

    fun languageStart(context: Context) {

        var langDevice = "en"
        var codeLang = "en"
        var position = 0
        var isLangDefault = false


        val listLanguage = mutableListOf<LanguageModel>()
        val listLang = listOf(
            LanguageModel("English", "en", false, R.drawable.ic_language_eng),
            LanguageModel("Hindi", "hi", false, R.drawable.ic_language_hi),
            LanguageModel("Spanish", "es", false, R.drawable.ic_language_es),
            LanguageModel("French", "fr", false, R.drawable.ic_language_fr),
            LanguageModel("Portuguese", "pt", false, R.drawable.ic_language_pt),
            LanguageModel("Indonesian", "in", false, R.drawable.ic_language_in),
            LanguageModel("German", "de", false, R.drawable.ic_language_de),
            LanguageModel("Japanese", "ja", false, R.drawable.ic_language_ja)
        )
        listLanguage.addAll(listLang)


        _langDevice.postValue(langDevice)
        _codeLang.postValue(codeLang)
        languages.postValue(listLanguage)

    }

    fun setSelectedLanguage(language: LanguageModel) {
        selectedLanguage.value = language
        _codeLang.postValue(language.code)
    }
}