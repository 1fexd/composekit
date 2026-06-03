package fe.android.preference.helper

import android.content.Context
import android.util.Log
import kotlin.concurrent.atomics.AtomicBoolean
import kotlin.concurrent.atomics.ExperimentalAtomicApi

@ExperimentalAtomicApi
public class PreferenceFileMigrator (
    private val context: Context,
    private val fileName: String,
    private val prefFileName: String
) {
    private val tag = "PreferenceFileMigrator_$fileName"
    private val migrated = AtomicBoolean(false)

    public fun migrate() {
        // Changed back in https://github.com/1fexd/android-pref-helper/commit/58bfd23d2e1e3ed6be44bcb4f9645308c1739741#diff-27d8a7cfe20d0dba8361d6f1a9a5607e7f1323d0d65a6720aedde827d7a4686aR13, need to migrate for very old installs
        Log.d(tag, "starting migrate")
        if (!migrated.compareAndSet(expectedValue = false, newValue = true)) {
            Log.d(tag, "already migrated")
            return
        }

        val sharedPrefsDir = context.dataDir.resolve("shared_prefs")
        val oldFile = sharedPrefsDir.resolve("$fileName.xml")
        val newFile = sharedPrefsDir.resolve("$prefFileName.xml")
        Log.d(tag, "Old file: $oldFile (${oldFile.exists()}), new file $newFile (${newFile.exists()})")
        if (oldFile.exists() && !newFile.exists()) {
            oldFile.copyTo(newFile)
            oldFile.delete()
            Log.d(tag, "migrated")
        } else {
            Log.d(tag, "nothing to migrate")
        }
    }
}
