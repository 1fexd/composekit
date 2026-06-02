@file:OptIn(ExperimentalUuidApi::class)

package fe.android.preference.helper

import android.os.Build
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNull
import assertk.assertions.isTrue
import de.infix.testBalloon.framework.core.TestConfig
import de.infix.testBalloon.framework.core.testSuite
import de.infix.testBalloon.integration.robolectric.ApplicationLifetime
import de.infix.testBalloon.integration.robolectric.RobolectricTestSuiteContent
import de.infix.testBalloon.integration.robolectric.robolectric
import de.infix.testBalloon.integration.robolectric.robolectricTestSuite
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

val PreferenceRepositoryTestSuite by testSuite {
    for (apiLevel in listOf(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)) {
        robolectricTestSuite(
            "API level $apiLevel",
            PreferenceRepositoryTest::class,
            testConfig = TestConfig.robolectric {
                sdk = apiLevel
                qualifiers = "xlarge-port"
                applicationLifetime = ApplicationLifetime.RobolectricTestSuite
            }
        )
    }
}

internal class PreferenceRepositoryTest : RobolectricTestSuiteContent({
    testSuite("setStringValueToPreference") {
        testSuite("string") {
            testFixture {
                val repository = createRepository()
                val definition = object : PreferenceDefinition() {
                    val stringTest = string("string_test")

                    init {
                        finalize()
                    }
                }

                repository to definition
            } asParameterForEach {
                test("happy path") { (repository, definition) ->
                    assertThat(repository.get(definition.stringTest)).isNull()
                    assertThat(
                        repository.setStringValueToPreference(
                            definition.stringTest,
                            ""
                        )
                    ).isTrue()
                    assertThat(repository.get(definition.stringTest)).isEqualTo("")
                }
            }
        }
        testSuite("boolean") {
            testFixture {
                val repository = createRepository()
                val definition = object : PreferenceDefinition() {
                    val boolTest = boolean("bool_test")

                    init {
                        finalize()
                    }
                }

                repository to definition
            } asParameterForEach {
                test("happy path") { (repository, definition) ->
                    assertThat(repository.get(definition.boolTest)).isFalse()
                    assertThat(
                        repository.setStringValueToPreference(
                            definition.boolTest,
                            "true"
                        )
                    ).isTrue()
                    assertThat(repository.get(definition.boolTest)).isTrue()
                    assertThat(
                        repository.setStringValueToPreference(
                            definition.boolTest,
                            "false"
                        )
                    ).isTrue()
                    assertThat(repository.get(definition.boolTest)).isFalse()
                }

                for (badValue in listOf("", "dummy")) {
                    test("bad path '$badValue'") { (repository, definition) ->
                        assertThat(repository.get(definition.boolTest)).isFalse()
                        assertThat(
                            repository.setStringValueToPreference(
                                definition.boolTest,
                                badValue
                            )
                        ).isFalse()
                        assertThat(repository.get(definition.boolTest)).isFalse()
                    }
                }
            }
        }
        testSuite("mapped") {
            testFixture {
                val repository = createRepository()
                val definition = object : PreferenceDefinition() {
                    val mappedTest = mapped("mapped_test", BrowserMode.None, BrowserMode)

                    init {
                        finalize()
                    }
                }

                repository to definition
            } asParameterForEach {
                test("happy path") { (repository, definition) ->
                    assertThat(repository.get(definition.mappedTest)).isEqualTo(BrowserMode.None)
                    assertThat(
                        repository.setStringValueToPreference(
                            definition.mappedTest,
                            "always_ask"
                        )
                    ).isTrue()
                    assertThat(repository.get(definition.mappedTest)).isEqualTo(BrowserMode.AlwaysAsk)
                    assertThat(
                        repository.setStringValueToPreference(
                            definition.mappedTest,
                            "whitelisted"
                        )
                    ).isTrue()
                    assertThat(repository.get(definition.mappedTest)).isEqualTo(BrowserMode.Whitelisted)
                }
                test("bad path") { (repository, definition) ->
                    assertThat(repository.get(definition.mappedTest)).isEqualTo(BrowserMode.None)
                    assertThat(
                        repository.setStringValueToPreference(
                            definition.mappedTest,
                            "AlwaysAsk"
                        )
                    ).isFalse()
                    assertThat(repository.get(definition.mappedTest)).isEqualTo(BrowserMode.None)
                }
            }
        }
    }
    testSuite("getAnyAsString") {
        testSuite("string") {
            testFixture {
                val repository = createRepository()
                val definition = object : PreferenceDefinition() {
                    val stringTest = string("string_test")

                    init {
                        finalize()
                    }
                }

                repository to definition
            } asParameterForEach {
                test("happy path") { (repository, definition) ->
                    assertThat(repository.get(definition.stringTest)).isNull()
                    repository.put(definition.stringTest, "hello world")
                    assertThat(repository.getAnyAsString(definition.stringTest)).isEqualTo("hello world")
                }
            }
        }
        testSuite("boolean") {
            testFixture {
                val repository = createRepository()
                val definition = object : PreferenceDefinition() {
                    val boolTest = boolean("bool_test")

                    init {
                        finalize()
                    }
                }

                repository to definition
            } asParameterForEach {
                test("happy path") { (repository, definition) ->
                    assertThat(repository.get(definition.boolTest)).isFalse()
                    repository.put(definition.boolTest, true)
                    assertThat(repository.getAnyAsString(definition.boolTest)).isEqualTo("true")
                }
            }
        }
        testSuite("mapped") {
            testFixture {
                val repository = createRepository()
                val definition = object : PreferenceDefinition() {
                    val mappedTest = mapped("mapped_test", BrowserMode.None, BrowserMode)

                    init {
                        finalize()
                    }
                }

                repository to definition
            } asParameterForEach {
                test("happy path") { (repository, definition) ->
                    assertThat(repository.getAnyAsString(definition.mappedTest)).isEqualTo("none")
                    repository.put(definition.mappedTest, BrowserMode.AlwaysAsk)
                    assertThat(repository.getAnyAsString(definition.mappedTest)).isEqualTo("always_ask")
                }
            }
        }
    }
})

fun createRepository(): PreferenceRepository {
    return object : PreferenceRepository(
        context = getApplicationContext(),
        fileName = Uuid.random().toString()
    ) {}
}


sealed class BrowserMode(val value: String) {
    object None : BrowserMode("none")
    object AlwaysAsk : BrowserMode("always_ask")
    object SelectedBrowser : BrowserMode("browser")
    object Whitelisted : BrowserMode("whitelisted")

    companion object Companion : OptionTypeMapper<BrowserMode, String>(
        { it.value }, { arrayOf(None, AlwaysAsk, SelectedBrowser, Whitelisted) }
    )
}
