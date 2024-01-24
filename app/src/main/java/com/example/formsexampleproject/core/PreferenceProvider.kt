package com.example.formsexampleproject.core

import android.content.SharedPreferences

interface PreferenceProvider {
    fun provideSharedPreference(name: String): SharedPreferences
}