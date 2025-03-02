package fe.std.process.android

import fe.std.process.ProcessStartConfig
import fe.std.process.Started

public object AndroidStarted : Started

public data object AndroidStartConfig : ProcessStartConfig<AndroidStarted> {
    override fun start(process: Process, runnable: Runnable): AndroidStarted {
        runnable.run()
        return AndroidStarted
    }
}
