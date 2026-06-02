package fe.composekit.preference.util

import fe.android.preference.helper.PreferenceRepository
import fe.android.preference.helper.compose.StatePreferenceRepository
import fe.composekit.preference.FlowPreferenceRepository

public fun PreferenceRepository.reload(key: String) {
    when (this) {
        is StatePreferenceRepository -> stateCache.get(key)?.forceRefresh()
        is FlowPreferenceRepository -> cache.get(key)?.reload()
    }
}
