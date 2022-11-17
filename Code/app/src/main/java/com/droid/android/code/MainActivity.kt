package com.droid.android.code

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.droid.android.code.databinding.ActivityMainBinding
import com.droid.network_observer.NetworkObserver
import com.droid.network_observer.NetworkState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  private lateinit var networkObserver: NetworkObserver

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    // --> Initialize the network observer in your activity or fragment
    networkObserver = NetworkObserver(this, lifecycle)
    lifecycle.addObserver(networkObserver)

    // --> Use live data to observe the network changes
    networkObserver.networkAvailableStateFlow.asLiveData().observe(this, Observer { networkState ->
      when (networkState) {
        NetworkState.Unavailable -> SnackBarDisplay.showNetworkUnavailableAlert(binding.root)
        NetworkState.Available -> SnackBarDisplay.removeNetworkUnavailableAlert()
      }
    })
  }

}
