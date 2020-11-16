package com.android.example.seminarmanager.ui.user

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.android.example.seminarmanager.R
import com.android.example.seminarmanager.databinding.ActivityLoginBinding
import com.android.example.seminarmanager.di.NetworkConst
import com.android.example.seminarmanager.ui.main.MainActivity
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity() {
    //di
    private val loginViewModel: LoginViewModel by viewModel()
    private val prefs : SharedPreferences by inject()
    //databinding
    private lateinit var binding: ActivityLoginBinding

    //gotoActivity
    private val gotoMainActivity : () -> (Unit) = {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(prefs.getString(NetworkConst.TOKEN_KEY, null) != null) {
            Toast.makeText(this, "자동로그인 됨", Toast.LENGTH_SHORT).show()
            gotoMainActivity()
        }
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
            if(it == "로그인 성공")
                gotoMainActivity()
        })
    }
}