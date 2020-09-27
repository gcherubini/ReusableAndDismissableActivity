package br.com.cherubini.reusabledismissibleactivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReusableViewModel(private val router: ReusableActivityRouter) : ViewModel() {

    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val inputText = MutableLiveData<String>()
    val buttonText = MutableLiveData<String>()
    private lateinit var viewType: ReusableScreenType

    fun load(
        title: String,
        description: String,
        buttonText: String,
        viewType: ReusableScreenType
    ) {
        this.title.value = title
        this.description.value = description
        this.buttonText.value = buttonText
        this.viewType = viewType
    }

    fun onButtonClick() {
        router.setResult(viewType, inputText.value)
        router.finish()
    }
}
