package com.example.formsexampleproject.core

import org.intellij.lang.annotations.Language

interface LanguageManager : ChooseLanguages, ChosenLanguage, Read<Int> {
    // implementation of language change
    abstract class Abstract(
        preferenceProvider: PreferenceProvider,
        preferenceName: String,
        private val key: String
    ) : LanguageManager {

        // инициализируем preference
        private val sharedPreferences = preferenceProvider.provideSharedPreference(preferenceName)

        // функция чтения ключа
        override fun read(): Int = sharedPreferences.getInt(key, -1)

        // сохраняем ключ
        override fun chooseKazakh() = save(KAZAKH)

        override fun chooseRussian() = save(RUSSIAN)

        // проверяем ключ
        override fun isChosenKazakh(): Boolean = read() == KAZAKH

        override fun isChosenRussian(): Boolean = read() == KAZAKH

        // используем preference для сохранения языка
        private fun save(language: Int) = sharedPreferences.edit().putInt(key, language).apply()

        private companion object {
            const val KAZAKH = 1
            const val RUSSIAN = 0
        }
    }

    class Base(preferenceProvider: PreferenceProvider) :
        Abstract(preferenceProvider, FILE_NAME, KEY) {
        private companion object {
            const val FILE_NAME = "languagesFileName"
            const val KEY = "languagesKey"
        }
    }

    class Change(
        private val language: LanguageManager,
        private val chooseLanguage: ChooseLanguages,
        private val chooseLanguagesResources: ChooseLanguages
    ) : LanguageManager {
        override fun chooseKazakh() {
            language.chooseKazakh()
            chooseLanguage.chooseKazakh()
            chooseLanguagesResources.chooseKazakh()
        }

        override fun chooseRussian() {
            language.chooseRussian()
            chooseLanguage.chooseRussian()
            chooseLanguagesResources.chooseRussian()
        }

        override fun isChosenKazakh(): Boolean = language.isChosenKazakh()

        override fun isChosenRussian(): Boolean = language.isChosenRussian()

        override fun read(): Int = language.read()

    }
}