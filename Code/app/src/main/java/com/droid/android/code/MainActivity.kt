package com.droid.android.code

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import com.droid.android.code.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.droid.android.code.monitor.NetworkMonitor
import com.droid.android.code.monitor.NetworkState
import com.droid.android.code.monitor.UnavailableConnectionLifecycleOwner
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Main Screen
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  @Inject
  lateinit var unavailableConnectionLifecycleOwner: UnavailableConnectionLifecycleOwner

  private lateinit var networkMonitor: NetworkMonitor
  private val networkObserver = NetworkObserver()

  private lateinit var binding: ActivityMainBinding
  private var snackbar: Snackbar? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    networkMonitor = NetworkMonitor(this, lifecycle)
    lifecycle.addObserver(networkMonitor)

    unavailableConnectionLifecycleOwner.addObserver(networkObserver)

    networkMonitor.networkAvailableStateFlow.asLiveData().observe(this, Observer { networkState ->
      handleNetworkState(networkState)
    })
  }

  private fun showNetworkUnavailableAlert(message: Int) {
    snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
        .setAction(R.string.retry) {
         Toast.makeText(this@MainActivity,"Retry clicked",Toast.LENGTH_LONG).show()
        }.apply {
          view.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.purple_500))
          show()
        }
  }

  private fun handleNetworkState(networkState: NetworkState?) {
    when (networkState) {
      NetworkState.Unavailable -> unavailableConnectionLifecycleOwner.onConnectionLost()
      NetworkState.Available -> unavailableConnectionLifecycleOwner.onConnectionAvailable()
      else -> {}
    }
  }

  private fun removeNetworkUnavailableAlert() {
    snackbar?.dismiss()
  }

  inner class NetworkObserver : DefaultLifecycleObserver {

    override fun onStart(owner: LifecycleOwner) {
      onNetworkAvailable()
    }

    override fun onStop(owner: LifecycleOwner) {
      onNetworkUnavailable()
    }

    private fun onNetworkUnavailable() {
      showNetworkUnavailableAlert(R.string.network_is_unavailable)
    }

    private fun onNetworkAvailable() {
      removeNetworkUnavailableAlert()
    }
  }
}
