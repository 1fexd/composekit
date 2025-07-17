package fe.android.preference.helper.testapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

class TestViewModel(
    private val testRepository: TestRepository,
    private val newTestRepository: NewTestRepository
) : ViewModel() {

    companion object {
        // Define a custom key for your dependency
        val TEST_REPOSITORY_KEY = object : CreationExtras.Key<TestRepository> {}
        val NEW_TEST_REPOSITORY_KEY = object : CreationExtras.Key<NewTestRepository> {}

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val testRepository = this[TEST_REPOSITORY_KEY] as TestRepository
                val newTestRepository = this[NEW_TEST_REPOSITORY_KEY] as NewTestRepository
                TestViewModel(testRepository, newTestRepository)
            }
        }
    }

//    val initState = testRepository.getOrPutInit(TestPreferenceDefinition.init)
//    val test123 = testRepository.asState(TestPreferenceDefinition.test123)

//    val newTest = newTestRepository.asViewModelState(TestPreferenceDefinition.newTest)
//    val newTestInt = newTestRepository.asFlow(TestPreferenceDefinition.newTestInt)

    val newTestInt = newTestRepository.asViewModelState(TestPreferenceDefinition.newTestInt)
}

class TestViewModel2(
    private val newTestRepository: NewTestRepository
) : ViewModel() {

    companion object {
        val NEW_TEST_REPOSITORY_KEY = object : CreationExtras.Key<NewTestRepository> {}

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val newTestRepository = this[NEW_TEST_REPOSITORY_KEY] as NewTestRepository
                TestViewModel2(newTestRepository)
            }
        }
    }

//    val initState = testRepository.getOrPutInit(TestPreferenceDefinition.init)
//    val test123 = testRepository.asState(TestPreferenceDefinition.test123)

    val newTestInt = newTestRepository.asViewModelState(TestPreferenceDefinition.newTestInt)
}
