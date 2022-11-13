/*
 * Copyright (c) 2022 Razeware LLC
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 * 
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.yourcompany.android.awarenessfood

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.yourcompany.android.awarenessfood.monitor.NetworkMonitor
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class NetworkMonitorTest {

  private val lifecycleOwner = mockk<LifecycleOwner>(relaxed = true)
  private val networkMonitor = mockk<NetworkMonitor>(relaxed = true)

  private lateinit var lifecycleRegistry: LifecycleRegistry

  @get:Rule
  var rule: TestRule = InstantTaskExecutorRule()

  @Before
  fun setup() {
    lifecycleRegistry = LifecycleRegistry(lifecycleOwner)
    lifecycleRegistry.addObserver(networkMonitor)
  }

  @Test
  fun `When dispatching ON_CREATE lifecycle event, call onCreate()`() {

    // Notify observers and set the lifecycle state.
    lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)

    // Verify the execution of the correct method.
    verify {
      networkMonitor.onCreate(lifecycleOwner)
    }
  }

  @Test
  fun `When dispatching ON_START lifecycle event, call onStart()`() {

    // Notify observers and set the lifecycle state.
    lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)

    // Verify the execution of the correct method.
    verify {
      networkMonitor.onStart(lifecycleOwner)
    }
  }

  @Test
  fun `When dispatching ON_STOP lifecycle event, call onStop()`() {

    // Notify observers and set the lifecycle state.
    lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)

    // Verify the execution of the correct method.
    verify {
      networkMonitor.onStop(lifecycleOwner)
    }
  }
}








