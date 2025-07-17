package fe.composekit.lifecycle.network.core

import android.net.NetworkCapabilities

public data class ConnectedNetwork(
    val networkCapabilities: NetworkCapabilities?,
    val isAvailable: Boolean,
    val isBlocked: Boolean,
) {
    public companion object {
        public val Unknown: ConnectedNetwork = ConnectedNetwork(
            networkCapabilities = null,
            isAvailable = false,
            isBlocked = false
        )

        private val CAPABILITIES = arrayOf(
            NetworkCapabilities.NET_CAPABILITY_INTERNET,
            NetworkCapabilities.NET_CAPABILITY_VALIDATED
        )

        private val TRANSPORTS = arrayOf(
            NetworkCapabilities.TRANSPORT_WIFI,
            NetworkCapabilities.TRANSPORT_CELLULAR,
            NetworkCapabilities.TRANSPORT_ETHERNET
        )
    }

    public fun isConnected(): Boolean {
        if (networkCapabilities == null) return false
        return isAvailable && !isBlocked
                && CAPABILITIES.all(networkCapabilities::hasCapability)
                && TRANSPORTS.any(networkCapabilities::hasTransport)
    }
}

