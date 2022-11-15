package com.droid.android.code

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.droid.android.code.monitor.NetworkObserver
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class CustomNetworkObserverTest {

  private val lifecycleOwner = mockk<LifecycleOwner>(relaxed = true)
  private val networkObserver = mockk<NetworkObserver>(relaxed = true)

  private lateinit var lifecycleRegistry: LifecycleRegistry

  @get:Rule
  var rule: TestRule = InstantTaskExecutorRule()

  @Before
  fun setup() {
    lifecycleRegistry = LifecycleRegistry(lifecycleOwner)
    lifecycleRegistry.addObserver(networkObserver)
  }

  @Test
  fun `When dispatching ON_CREATE lifecycle event, call onCreate()`() {

    // Notify observers and set the lifecycle state.
    lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)

    // Verify the execution of the correct method.
    verify {
      networkObserver.onCreate(lifecycleOwner)
    }
  }

  @Test
  fun `When dispatching ON_START lifecycle event, call onStart()`() {

    // Notify observers and set the lifecycle state.
    lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)

    // Verify the execution of the correct method.
    verify {
      networkObserver.onStart(lifecycleOwner)
    }
  }

  @Test
  fun `When dispatching ON_STOP lifecycle event, call onStop()`() {

    // Notify observers and set the lifecycle state.
    lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)

    // Verify the execution of the correct method.
    verify {
      networkObserver.onStop(lifecycleOwner)
    }
  }
}








