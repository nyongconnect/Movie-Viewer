package com.nyongnsikak.movieviewer.utils

import android.content.Context
import android.content.SharedPreferences
import com.nyongnsikak.movieviewer.utils.AppConstants.APP_SHARED_PREFERENCE
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PreferenceHelper @Inject constructor(@ApplicationContext context: Context) {

    val preference : SharedPreferences = context.getSharedPreferences(APP_SHARED_PREFERENCE, Context.MODE_PRIVATE)


    var isLinear : Boolean
        get()= preference.getBoolean("bool", false)
        set(value) = preference.edit().putBoolean("bool", value).apply()
}