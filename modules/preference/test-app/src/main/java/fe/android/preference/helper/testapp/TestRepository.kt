package fe.android.preference.helper.testapp

import android.content.Context
import fe.android.preference.helper.compose.StatePreferenceRepository
import fe.composekit.preference.FlowPreferenceRepository


class TestRepository(context: Context) : StatePreferenceRepository(context) {
    //        val testState = asState(TestPreferenceDefinition.test)

}

class NewTestRepository(context: Context) : FlowPreferenceRepository(context) {

}
