package com.droid.android.code.monitor

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkRequest
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * This class is in charge of listening to the state of the network connection and notifying the
 * activity if the state of the connection changes.
 * */
class NetworkMonitor constructor(
    private val context: Context,
    private val lifecycle: Lifecycle
  ) : DefaultLifecycleObserver {

  private lateinit var networkCallback: ConnectivityManager.NetworkCallback
  private var connectivityManager: ConnectivityManager? = null
  private val validNetworks = HashSet<Network>()

  private lateinit var job: Job
  private lateinit var coroutineScope: CoroutineScope

  private val _networkAvailableStateFlow: MutableStateFlow<NetworkState> = MutableStateFlow(NetworkState.Available)
  val networkAvailableStateFlow
    get() = _networkAvailableStateFlow

  override fun onCreate(owner: LifecycleOwner) {
    super.onCreate(owner)
    init()
  }

  override fun onStart(owner: LifecycleOwner) {
    super.onStart(owner)
    registerNetworkCallback()
  }

  override fun onStop(owner: LifecycleOwner) {
    super.onStop(owner)
    unregisterNetworkCallback()
  }

  private fun init() {
    connectivityManager =
        context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
  }

  private fun registerNetworkCallback() {
    // With this condition, NetworkMonitor will only start monitoring the network connection when
    // the parent lifecycle is at least in the STARTED state.
    if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
      initCoroutine()
      initNetworkMonitoring()
    }
  }

  private fun unregisterNetworkCallback() {
    validNetworks.clear()
    connectivityManager?.unregisterNetworkCallback(networkCallback)
    job.cancel()
  }

  private fun initCoroutine() {
    job = Job()
    coroutineScope = CoroutineScope(Dispatchers.Default + job)
  }

  private fun initNetworkMonitoring() {
    networkCallback = createNetworkCallback()

    val networkRequest = NetworkRequest.Builder()
        .addCapability(NET_CAPABILITY_INTERNET)
        .build()
    connectivityManager?.registerNetworkCallback(networkRequest, networkCallback)
  }

  private fun createNetworkCallback() = object : ConnectivityManager.NetworkCallback() {
    override fun onAvailable(network: Network) {
      connectivityManager?.getNetworkCapabilities(network).also {
        if (it?.hasCapability(NET_CAPABILITY_INTERNET) == true) {
          validNetworks.add(network)
        }
      }
      checkValidNetworks()
    }

    override fun onLost(network: Network) {
      validNetworks.remove(network)
      checkValidNetworks()
    }
  }

  private fun checkValidNetworks() {
    coroutineScope.launch {
      _networkAvailableStateFlow.emit(
          if (validNetworks.size > 0)
            NetworkState.Available
          else
            NetworkState.Unavailable
      )
    }
  }
}

sealed class NetworkState {
  object Unavailable : NetworkState()
  object Available : NetworkState()
}