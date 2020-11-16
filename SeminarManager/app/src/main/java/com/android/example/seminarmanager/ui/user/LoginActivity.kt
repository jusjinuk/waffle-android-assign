package com.android.example.seminarmanager.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.android.example.seminarmanager.R
import com.android.example.seminarmanager.databinding.ActivityLoginBinding
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModel()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_login
        )
        binding.run {
            lifecycleOwner = this@LoginActivity
            viewModel = loginViewModel
            loginButton.setOnClickListener {
                loginViewModel.login()
            }
        }
        loginViewModel.toast.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}