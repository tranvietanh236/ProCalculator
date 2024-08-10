package com.V1solution.procaculator.ui.languagesetting

import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.V1solution.procaculator.base.BaseViewModel
import com.V1solution.procaculator.model.LanguageModel
import com.V1solution.procaculator.utils.SystemUtil
import com.travietnanh.procaculator.R
import java.util.Locale

class LanguageSettingViewModel : BaseViewModel() {
    val languages = MutableLiveData<List<LanguageModel>>()
    val selectedLanguage = MutableLiveData<LanguageModel>()

    private val _langDevice = MutableLiveData<String>()
    val langDevice: LiveData<String>
        get() = _langDevice

    private val _codeLang = MutableLiveData<String>()
    val codeLang: LiveData<String>
        get() = _codeLang

    fun language(context: Context) {

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

        if (SystemUtil.getActive(context, false)) {
            val locale: Locale =
                Resources.getSystem().configuration.locales[0]

            langDevice = locale.language

            for ((index, languageModel) in listLanguage.withIndex()) {
                if (languageModel.code.contains(langDevice.toLowerCase())) {
                    isLangDefault = true
                    position = index
                    break
                }
            }

            if (position > 0 && isLangDefault) {
                val languageModel = listLanguage[position]
                listLanguage.removeAt(position)
                listLanguage.add(0, languageModel)
            }


            var check = -1
            for (i in listLanguage.indices) {
                val languageModel = listLanguage[i]
                if (languageModel.code.contains(SystemUtil.getPreLanguage(context).toString())) {
                    listLanguage[i].active = true
                    listLanguage.remove(languageModel)
                    listLanguage.add(0, languageModel)
                    check = i
                    break
                }
            }
            listLanguage[0].active = true
            codeLang = listLanguage[0].code
        } else {
            val locale: Locale =
                Resources.getSystem().configuration.locales[0]

            langDevice = locale.language

            for ((index, languageModel) in listLanguage.withIndex()) {
                if (languageModel.code.contains(langDevice.lowercase(Locale.getDefault()))) {
                    isLangDefault = true
                    position = index
                    break
                }
            }

            if (position > 0 && isLangDefault) {
                val languageModel = listLanguage[position]
                listLanguage.removeAt(position)
                listLanguage.add(0, languageModel)
            }

            listLanguage[0].active = true
            codeLang = listLanguage[0].code

        }

        _langDevice.postValue(langDevice)
        _codeLang.postValue(codeLang)
        languages.postValue(listLanguage)

    }

    fun setSelectedLanguage(language: LanguageModel) {
        selectedLanguage.value = language
        _codeLang.postValue(language.code)
    }
}