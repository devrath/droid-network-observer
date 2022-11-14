package com.yourcompany.android.awarenessfood.monitor

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnavailableConnectionLifecycleOwner @Inject constructor(): LifecycleOwner {

    // Step 1: Implement LifecycleOwner interface
    // Step 2: Initialize LifecycleRegistry
    // Step 3: Override getLifecycle() and return LifecycleRegistry instance
    // Step 4: Emit lifecycle events and notify observers

    private val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    fun onConnectionAvailable() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    fun onConnectionLost() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    }

    fun addObserver(lifecycleObserver: LifecycleObserver) {
        lifecycleRegistry.addObserver(lifecycleObserver)
    }
}
