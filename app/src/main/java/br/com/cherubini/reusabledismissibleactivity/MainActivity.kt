package br.com.cherubini.reusabledismissibleactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import br.com.cherubini.reusabledismissibleactivity.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var router: ReusableActivityRouter
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        binding.lifecycleOwner = this

        router = ReusableActivityRouter(this)
        viewModel = MainActivityViewModel(router)

        binding.viewModel = viewModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == ReusableActivityRouter.REUSABLE_ACTIVITY_REQUEST_CODE) {
            val resultExtra = data?.getParcelableExtra<ReusableResultExtra>(ReusableActivityRouter.REUSABLE_ACTIVITY_RESULT_KEY)
            resultExtra?.run {
                Toast.makeText(this@MainActivity,
                    "The reusable closed screen was: $screenType " +
                            "with the inputted text $inputtedText",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}