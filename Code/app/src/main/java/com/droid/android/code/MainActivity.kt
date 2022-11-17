package com.droid.android.code

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import com.droid.android.code.databinding.ActivityMainBinding
import com.droid.network_observer.NetworkObserver
import com.droid.network_observer.NetworkState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * Main Screen
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private lateinit var networkMonitor: NetworkObserver

  private lateinit var binding: ActivityMainBinding
  private var snackbar: Snackbar? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    networkMonitor = NetworkObserver(this, lifecycle)
    lifecycle.addObserver(networkMonitor)

    networkMonitor.networkAvailableStateFlow.asLiveData().observe(this, Observer { networkState ->
      handleNetworkState(networkState)
    })
  }

  private fun showNetworkUnavailableAlert(message: Int) {
    snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
      .setActionTextColor(ContextCompat.getColor(this@MainActivity, R.color.white))
      .setAction(R.string.retry) {
         Toast.makeText(this@MainActivity,"Retry clicked",Toast.LENGTH_LONG).show()
        }.apply {
          view.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.colorRed))
          show()
        }
  }

  private fun handleNetworkState(networkState: NetworkState?) {
    when (networkState) {
      NetworkState.Unavailable -> showNetworkUnavailableAlert(R.string.network_is_unavailable)
      NetworkState.Available -> removeNetworkUnavailableAlert()
      else -> {}
    }
  }

  private fun removeNetworkUnavailableAlert() {
    snackbar?.let {
      val view: View = it.view

      val snackBarText = view.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
      snackBarText.text = resources.getText(R.string.network_is_available)

      view.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.colorGreen))
    }
    //snackbar?.dismiss()
  }

}
