package br.com.cherubini.reusabledismissibleactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity


class ReusableActivityRouter(private val activity: AppCompatActivity) {

    companion object {
        const val REUSABLE_ACTIVITY_EXTRA = "reusable_activity_extra"
        const val REUSABLE_ACTIVITY_REQUEST_CODE = 5
        const val REUSABLE_ACTIVITY_RESULT_KEY = "reusable_activity_result"
    }

    fun showWelcomeScreen() {
        showReusableActivity(ReusableActivityExtra(
            "Greetings",
            "Welcome to this application! What is your name?",
            "Enter",
            ReusableScreenType.WELCOME
        ))
    }

    fun showTermsAndConditionsScreen() {
        showReusableActivity(ReusableActivityExtra(
            "Help us with a Feedback",
            "In one word, how was your experience using this application?",
            "Send",
            ReusableScreenType.FEEDBACK
        ))
    }

    private fun showReusableActivity(extra: ReusableActivityExtra) {
        val extras = Intent(activity, ReusableActivity::class.java).apply {
            putExtra(
                REUSABLE_ACTIVITY_EXTRA, extra
            )
        }
        activity.startActivityForResult(extras, REUSABLE_ACTIVITY_REQUEST_CODE)
    }

    fun setResult(screenType: ReusableScreenType, inputtedText: String?) {
        val resultIntent = Intent().apply {
            putExtra(
                REUSABLE_ACTIVITY_RESULT_KEY, ReusableResultExtra(
                    inputtedText,
                    screenType
                )
            )
        }
        activity.setResult(Activity.RESULT_OK, resultIntent)
    }

    fun finish() {
        activity.finish()
    }
}
