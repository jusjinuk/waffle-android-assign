package com.android.example.seminarmanager.ui.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.android.example.seminarmanager.R
import com.android.example.seminarmanager.databinding.ActivitySignupBinding
import com.android.example.seminarmanager.ui.SingleEvent
import com.android.example.seminarmanager.ui.main.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SignupActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding
    val signupViewModel: SignupViewModel by viewModel()

    //gotoActivity
    private val gotoMainActivity: () -> (Unit) = {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)

        binding.run {
            viewModel = signupViewModel
            lifecycleOwner = this@SignupActivity
            createButton.setOnClickListener {
                signupViewModel.signup()
            }
        }
        SingleEvent.triggerToast.observe(this, Observer { e ->
            e.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })
        SingleEvent.navigateToActivity.observe(this, Observer { e ->
            e.getContentIfNotHandled()?.let {
                when (it) {
                    SingleEvent.MAIN_ACTIVITY -> gotoMainActivity()
                    else -> {
                    }
                }
            }
        })
    }
}