package com.droid.network_observer

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnavailableConnectionLifecycleOwner @Inject constructor(): LifecycleOwner {

    // Step 4: Emit lifecycle events and notify observers

    // Initialize the LifecycleRegistry
    private val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    // Override getLifeCycle() and return lifecycle registry instance
    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    // Emit OnStart event when connection is available
    fun onConnectionAvailable() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    // Emit OnStop event when connection is not available
    fun onConnectionLost() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    }

    // Add life cycle observer from the activity
    fun addObserver(lifecycleObserver: LifecycleObserver) {
        lifecycleRegistry.addObserver(lifecycleObserver)
    }
}
