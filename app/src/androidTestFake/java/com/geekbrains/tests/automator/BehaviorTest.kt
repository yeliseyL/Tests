package com.geekbrains.tests.automator

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import com.geekbrains.tests.*
import com.geekbrains.tests.TEST_NUMBER_OF_RESULTS_MINUS_1
import com.geekbrains.tests.TEST_NUMBER_OF_RESULTS_PLUS_1
import com.geekbrains.tests.TEST_NUMBER_OF_RESULTS_ZERO
import com.geekbrains.tests.TIMEOUT
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class BehaviorTest {

    private val uiDevice: UiDevice = UiDevice.getInstance(getInstrumentation())

    private val context = ApplicationProvider.getApplicationContext<Context>()

    private val packageName = context.packageName

    @Before
    fun setup() {
        uiDevice.pressHome()

        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)

        uiDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), TIMEOUT)
    }

    @Test
    fun test_MainActivityIsStarted() {
        val editText = uiDevice.findObject(By.res(packageName, SEARCH_EDIT_TEXT))
        Assert.assertNotNull(editText)
    }

    @Test
    fun test_SearchIsPositive() {
        val editText = uiDevice.findObject(By.res(packageName, SEARCH_EDIT_TEXT))
        editText.text = SEARCH_TEXT

        val showResults: UiObject2 = uiDevice.findObject(
            By.res(
                packageName,
                SHOW_RESULTS_BUTTON
            )
        )
        showResults.click()

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, TOTAL_COUNT_TEXT_VIEW)),
                TIMEOUT
            )

        Assert.assertEquals(changedText.text.toString(), TEST_NUMBER_OF_RESULTS_42)
    }

    @Test
    fun test_OpenDetailsScreen() {
        val toDetails: UiObject2 = uiDevice.findObject(
            By.res(
                packageName,
                DETAILS_ACTIVITY_BUTTON
            )
        )
        toDetails.click()

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, TOTAL_COUNT_TEXT_VIEW)),
                TIMEOUT
            )

        Assert.assertEquals(changedText.text, TEST_NUMBER_OF_RESULTS_ZERO)
    }

    @Test
    fun test_CorrectResultsOnDetailsScreen() {
        val editText = uiDevice.findObject(By.res(packageName, SEARCH_EDIT_TEXT))
        editText.text = SEARCH_TEXT

        val showResults: UiObject2 = uiDevice.findObject(
            By.res(
                packageName,
                SHOW_RESULTS_BUTTON
            )
        )
        showResults.click()

        val toDetails =
            uiDevice.wait(
                Until.findObject(By.res(packageName, DETAILS_ACTIVITY_BUTTON)),
                TIMEOUT
            )
        toDetails.click()

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, TOTAL_COUNT_TEXT_VIEW)),
                TIMEOUT
            )

        Assert.assertEquals(changedText.text, TEST_NUMBER_OF_RESULTS_42)
    }

    @Test
    fun test_IncrementButtonWorking() {
        val toDetails: UiObject2 = uiDevice.findObject(
            By.res(packageName, DETAILS_ACTIVITY_BUTTON)
        )
        toDetails.click()

        val plus =
            uiDevice.wait(
                Until.findObject(By.res(packageName, INCREMENT_BUTTON)),
                TIMEOUT
            )
        plus.click()

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, TOTAL_COUNT_TEXT_VIEW)),
                TIMEOUT
            )

        Assert.assertEquals(changedText.text, TEST_NUMBER_OF_RESULTS_PLUS_1)
    }

    @Test
    fun test_DecrementButtonWorking() {
        val toDetails: UiObject2 = uiDevice.findObject(
            By.res(packageName, DETAILS_ACTIVITY_BUTTON)
        )
        toDetails.click()

        val plus =
            uiDevice.wait(
                Until.findObject(By.res(packageName, DECREMENT_BUTTON)),
                TIMEOUT
            )
        plus.click()

        val changedText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, TOTAL_COUNT_TEXT_VIEW)),
                TIMEOUT
            )

        Assert.assertEquals(changedText.text, TEST_NUMBER_OF_RESULTS_MINUS_1)
    }

}
