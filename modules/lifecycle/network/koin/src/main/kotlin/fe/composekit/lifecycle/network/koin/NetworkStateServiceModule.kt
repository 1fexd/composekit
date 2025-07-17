package fe.composekit.lifecycle.network.koin

import android.net.ConnectivityManager
import androidx.core.content.getSystemService
import fe.android.lifecycle.koin.extension.service
import fe.composekit.lifecycle.network.core.NetworkStateService
import org.koin.core.module.Module
import org.koin.dsl.module


public val NetworkStateServiceModule: Module = module {
    service<NetworkStateService> {
        NetworkStateService(connectivityManager = applicationContext.getSystemService<ConnectivityManager>()!!)
    }
}
