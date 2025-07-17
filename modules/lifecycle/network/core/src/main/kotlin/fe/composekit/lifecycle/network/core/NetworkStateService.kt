package fe.composekit.lifecycle.network.core

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import androidx.lifecycle.LifecycleOwner
import fe.android.lifecycle.LifecycleAwareService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update

public class NetworkStateService(
    private val connectivityManager: ConnectivityManager,
) : LifecycleAwareService, ConnectivityManager.NetworkCallback() {

    private val _currentNetwork = MutableStateFlow(ConnectedNetwork.Unknown)
    public val currentNetwork: StateFlow<ConnectedNetwork> = _currentNetwork.asStateFlow()

    public val isNetworkConnected: Boolean
        get() = _currentNetwork.value.isConnected()

    override suspend fun onAppInitialized(owner: LifecycleOwner) {
        connectivityManager.registerDefaultNetworkCallback(this)
    }

    public suspend fun awaitNetworkConnection(): ConnectedNetwork {
        return if (isNetworkConnected) _currentNetwork.value
        else _currentNetwork.first { it.isConnected() }
    }

    override fun onAvailable(network: Network) {
        _currentNetwork.update { it.copy(isAvailable = true) }
    }

    override fun onLost(network: Network) {
        _currentNetwork.update { it.copy(isAvailable = false, networkCapabilities = null) }
    }

    override fun onUnavailable() {
        _currentNetwork.update { it.copy(isAvailable = false, networkCapabilities = null) }
    }

    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        _currentNetwork.update { it.copy(networkCapabilities = networkCapabilities) }
    }

    override fun onBlockedStatusChanged(network: Network, blocked: Boolean) {
        _currentNetwork.update { it.copy(isBlocked = blocked) }
    }
}
