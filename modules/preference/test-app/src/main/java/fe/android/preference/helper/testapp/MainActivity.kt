package fe.android.preference.helper.testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.MutableCreationExtras

class MainActivity : ComponentActivity() {

    val viewModelStoreOwner: ViewModelStoreOwner = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val newTestRepository = NewTestRepository(this@MainActivity)

        val testViewModel: TestViewModel = ViewModelProvider.create(
            viewModelStoreOwner,
            factory = TestViewModel.Factory,
            extras = MutableCreationExtras().apply {
                set(TestViewModel.TEST_REPOSITORY_KEY, TestRepository(this@MainActivity))
                set(TestViewModel.NEW_TEST_REPOSITORY_KEY, newTestRepository)
            },
        )[TestViewModel::class]

        val testViewModel2: TestViewModel2 = ViewModelProvider.create(
            viewModelStoreOwner,
            factory = TestViewModel2.Factory,
            extras = MutableCreationExtras().apply {
                set(TestViewModel2.NEW_TEST_REPOSITORY_KEY, newTestRepository)
            },
        )[TestViewModel2::class]

        setContent {
            Box(
                modifier = Modifier
                    .safeContentPadding()
                    .fillMaxSize()
                    .padding(all = 10.dp)
            ) {
                Box(modifier = Modifier.align(Alignment.TopCenter)) {
                    TestComposablePageWrapper(testViewModel)
                }
                Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                    TestComposablePage2Wrapper(testViewModel2)
                }
            }
        }
//        TestPreferenceDefinition.runMigrations(preferenceRepository)
//        println(preferenceRepository.initState)s

//        val test = preferenceRepository.asState(TestPreferenceDefinition.int)
//        val test123 = preferenceRepository.asState(TestPreferenceDefinition.test123)
//        preferenceRepository.unsafePut()
//        println(test.value)
//        println(test())
//        Log.d("MigrateTo(test123)", "current=${test123()}")
//        Test.i
//        Test.intPreference("")

//        test.updateState(2)
//        test(2)

//        preferenceRepository.edit {
//
//        }

//        preferenceRepository.editor { pref ->
//
////            pref.writeInt(Test.int, 1, this)
//        }

//        println(preferenceRepository.getAnyAsString(TestPreferenceDefinition.test))
//        )
//        preferenceRepository.edit {
//            put(Test.int, 10)
//        }
//
//        val hi = test() == 3
//        println(hi)
//        val x by test

//        val pref = Test.int


//        val test123 = preferenceRepository.get(Test.int)

//        val x = test

//        Preference.PB()
//        val current = test.value

//        val cur = test()


//        Log.d("test", preferenceRepository.getAnyAsString(Test.test))
//        registerPreference {
//            val key = stringPreference("value")
//        }


//        val key = LinkSheetPreferences.test


////        preferenceRepository.getStringState()
//    //
//    //        val str = preferenceRepository.getAsString(key)
//
//        preferenceRepository.editor {
//            preferenceRepository.writeString(preference, "new value", this)
//            preferenceRepository.writeString(preference, "new value2", this)
//        }

    }
}
