package br.com.cherubini.reusabledismissibleactivity

import androidx.lifecycle.ViewModel

class MainActivityViewModel(private val router: ReusableActivityRouter) : ViewModel() {
    fun onWelcomeActivityButtonClick() {
        router.showWelcomeScreen()
    }

    fun onFeedbackButtonClick() {
        router.showTermsAndConditionsScreen()
    }
}
