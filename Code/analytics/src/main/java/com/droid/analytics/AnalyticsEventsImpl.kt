package com.droid.analytics

import android.util.Log
import javax.inject.Inject

class AnalyticsEventsImpl @Inject constructor() : AnalyticsEvents {

  companion object {
    private const val TAG = "APP_LOGGER"
  }

  override fun trackAppEvent(event: String) {
    Log.i(TAG, "App is going to $event")
  }
}