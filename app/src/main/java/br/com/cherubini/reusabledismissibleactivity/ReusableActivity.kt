package br.com.cherubini.reusabledismissibleactivity

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.cherubini.reusabledismissibleactivity.databinding.ReusableActivityBinding
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReusableActivityExtra(
    val title: String,
    val description: String,
    val buttonText: String,
    val viewType: ReusableScreenType
) : Parcelable

@Parcelize
data class ReusableResultExtra(
    val inputtedText: String?,
    val screenType: ReusableScreenType
) : Parcelable

enum class ReusableScreenType { WELCOME, FEEDBACK }

class ReusableActivity : AppCompatActivity() {

    private lateinit var router: ReusableActivityRouter
    private lateinit var viewModel: ReusableViewModel
    private lateinit var binding: ReusableActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.reusable_activity)
        binding.lifecycleOwner = this

        router = ReusableActivityRouter(this)
        viewModel = ReusableViewModel(router)

        binding.viewModel = viewModel

        loadScreenWithExtra()
    }

    private fun loadScreenWithExtra() {
        val extra = intent.getParcelableExtra<ReusableActivityExtra>(
            ReusableActivityRouter.REUSABLE_ACTIVITY_EXTRA
        )
        requireNotNull(extra).run {
            viewModel.load(
                title,
                description,
                buttonText,
                viewType
            )
        }
    }
}
