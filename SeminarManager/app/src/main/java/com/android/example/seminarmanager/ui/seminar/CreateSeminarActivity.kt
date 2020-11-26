package com.android.example.seminarmanager.ui.seminar

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TimePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.android.example.seminarmanager.R
import com.android.example.seminarmanager.databinding.ActivityCreateSeminarBinding
import com.android.example.seminarmanager.ui.SingleEvent
import com.android.example.seminarmanager.ui.main.MainActivity
import java.text.SimpleDateFormat
import java.util.*
import org.koin.android.viewmodel.ext.android.viewModel

class CreateSeminarActivity : AppCompatActivity() {

    lateinit var binding: ActivityCreateSeminarBinding
    private val createSeminarViewModel: CreateSeminarViewModel by viewModel()

    //gotoActivity
    private val gotoMainActivity: () -> (Unit) = {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_seminar)
        binding.run {
            viewModel = createSeminarViewModel
            lifecycleOwner = this@CreateSeminarActivity
            timeButton.setOnClickListener {
                val cal = Calendar.getInstance()
                val timeSetListener =
                    TimePickerDialog.OnTimeSetListener { view: TimePicker?, hourOfDay: Int, minute: Int ->
                        cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        cal.set(Calendar.MINUTE, minute)
                        createSeminarViewModel.time.value =
                            SimpleDateFormat("HH:mm").format(cal.time)
                    }
                TimePickerDialog(
                    this@CreateSeminarActivity,
                    timeSetListener,
                    cal.get(Calendar.HOUR_OF_DAY),
                    cal.get(Calendar.MINUTE),
                    true
                ).show()
            }
            createButton.setOnClickListener {
                createSeminarViewModel.createSeminar()
            }
        }
        SingleEvent.triggerToast.observe(this, androidx.lifecycle.Observer { e ->
            e.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })
        SingleEvent.navigateToActivity.observe(this, androidx.lifecycle.Observer { e ->
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