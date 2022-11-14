package com.droid.android.code

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.droid.android.code.analytics.AppGlobalEvents
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class RecipesApplication : Application() {

  @Inject
  lateinit var appGlobalEvents: AppGlobalEvents

  override fun onCreate() {
    super.onCreate()

    ProcessLifecycleOwner.get().lifecycle.addObserver(appGlobalEvents)
  }
}