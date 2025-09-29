package fe.composekit.extension

import android.content.ClipData
import android.content.ClipboardManager

public fun ClipboardManager.setText(label: String, text: String) {
    setPrimaryClip(ClipData.newPlainText(label, text))
}

public fun ClipboardManager.getFirstText(): String? {
    return primaryClip?.takeIf { it.itemCount > 0 }?.getItemAt(0)?.text?.toString()
}
