package smir.shitab14.networkcheck

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Flow Collect
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.status.collect() {
                        openSnackBar(it.name)
                    }
                }
            }
        }

        viewModel.observeNetworkState(context)
    }

    private fun openSnackBar(text: String) {
        val snackBar = Snackbar.make(
            findViewById(android.R.id.content),
            text,
            Snackbar.LENGTH_INDEFINITE
        )
        snackBar.show()
    }
}