package fe.android.preference.helper.testapp

import fe.android.preference.helper.PreferenceDefinition

object TestPreferenceDefinition : PreferenceDefinition() {
    val test = mapped("key", BrowserMode.SelectedBrowser, BrowserMode.Companion)
    val int = int("testint").migrate { repo, value ->
        if (!repo.hasStoredValue(test)) {

        }
    }

    val test123 = long(".________________________.", 1111).migrateTo { preferenceRepository, current ->
        current + 1000
    }

    val init = string("tet") {
        "yeeeeeeeeet"
    }

    val counter = int("counter")

    val newTest = string("newTest")
    val newTestInt = int("newTestInt")
    val newTestInt2 = int("newTestInt2")

    init {
        finalize()
    }
}


