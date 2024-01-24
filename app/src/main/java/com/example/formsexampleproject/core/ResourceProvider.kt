package com.example.formsexampleproject.core

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.LocaleList
import android.support.annotation.StringRes
import java.util.Locale

interface ResourceProvider : ChooseLanguages, PreferenceProvider {

    fun string(@StringRes id: Int): String
    fun string(@StringRes id: Int, vararg args: Any): String

    class Base(private var context: Context) : ResourceProvider {
        override fun string(id: Int) = context.getString(id)
        override fun string(id: Int, vararg args: Any) = context.getString(id, *args)

        override fun provideSharedPreference(name: String): SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

        override fun chooseKazakh() = changeLanguage("en", Locale("kk", "KZ"))
        override fun chooseRussian() = changeLanguage("ru", Locale("ru", "RU"))

        private fun changeLanguage(lang: String, locale: Locale) {
            val conf = context.resources.configuration
            conf.setLocales(LocaleList.forLanguageTags(lang))
            context = context.createConfigurationContext(conf)
        }
    }
}