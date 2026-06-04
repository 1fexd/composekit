package fe.composekit.log

import fe.composekit.mozilla.components.support.base.log.logger.Logger

inline fun <reified T> createLogger(): Logger {
    return Logger(T::class.simpleName ?: "<empty>")
}
