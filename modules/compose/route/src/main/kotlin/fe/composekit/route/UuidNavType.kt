@file:OptIn(ExperimentalUuidApi::class)

package fe.composekit.route

import androidx.navigation.NavType
import androidx.savedstate.SavedState
import androidx.savedstate.read
import androidx.savedstate.write
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

public object UuidNavType : NavType<Uuid>(false) {
    override val name: String
        get() = "uuid"

    override fun put(bundle: SavedState, key: String, value: Uuid) {
        bundle.write {
            putString(key, value.toString())
        }
    }

    override fun get(bundle: SavedState, key: String): Uuid {
        return bundle.read {
            Uuid.parse(getString(key))
        }
    }

    override fun parseValue(value: String): Uuid {
        return Uuid.parse(value)
    }
}
