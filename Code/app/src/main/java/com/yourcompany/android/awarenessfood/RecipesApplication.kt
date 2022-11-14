package com.yourcompany.android.awarenessfood

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.yourcompany.android.awarenessfood.analytics.AppGlobalEvents
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