package fe.android.compose.extension

import android.content.ClipData
import androidx.compose.ui.platform.ClipboardManager
import fe.android.compose.clipboard.ClipboardDefaults

fun ClipboardManager.setText(charSequence: CharSequence) {
    nativeClipboard.setPlainText(charSequence)
}

fun android.content.ClipboardManager.setPlainText(charSequence: CharSequence) {
    setPrimaryClip(ClipData.newPlainText(ClipboardDefaults.PlainTextLabel, charSequence))
}
