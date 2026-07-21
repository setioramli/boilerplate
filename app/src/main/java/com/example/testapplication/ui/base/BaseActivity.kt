package com.example.testapplication.ui.base

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.testapplication.R
import com.example.testapplication.utils.NetworkUtils

abstract class BaseActivity<T: ViewBinding>(): AppCompatActivity() {

    private lateinit var _binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBeforeViewCreated()
        _binding = getBindingFactory()(layoutInflater)
        setContentView(_binding.root)

        _binding.onViewCreated(savedInstanceState)
    }

    abstract fun T.onViewCreated(savedInstanceState: Bundle?)

    abstract fun getBindingFactory(): (LayoutInflater) -> T

    protected open fun onBeforeViewCreated(){
    }

    protected fun showErrorDialog(
        title: String = getString(R.string.txt_error), // Use a generic error title
        message: String,
        retryAction: () -> Unit
    ) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(getString(R.string.txt_retry)) { dialog, _ ->
                dialog.dismiss()
                retryAction() // Execute the provided retry action
            }
            .setNegativeButton(getString(R.string.txt_cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    protected fun handleNetworkAction(actionToPerform: () -> Unit) {
        if (NetworkUtils.isInternetAvailable(this)) {
            actionToPerform()
        } else {
            showErrorDialog(
                title = getString(R.string.txt_no_internet_connection),
                message = getString(R.string.txt_check_your_internet_connection)
            ) {
                handleNetworkAction(actionToPerform)
            }
        }
    }
}