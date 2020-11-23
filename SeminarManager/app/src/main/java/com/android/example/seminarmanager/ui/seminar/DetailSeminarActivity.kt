package com.android.example.seminarmanager.ui.seminar

import android.app.Person
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.example.seminarmanager.R
import com.android.example.seminarmanager.databinding.ActivityDetailSeminarBinding
import com.android.example.seminarmanager.repo.Role.INSTRUCTOR
import com.android.example.seminarmanager.repo.Role.PARTICIPANT
import com.android.example.seminarmanager.ui.SingleEvent
import com.android.example.seminarmanager.ui.main.seminar.SeminarViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailSeminarActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailSeminarBinding
    val detailSeminarViewModel: DetailSeminarViewModel by viewModel()

    companion object {
        const val SEMINAR_ID = "seminar_id"

        fun intentWithSeminarId(context: Context, id: Int): Intent =
            Intent(context, DetailSeminarActivity::class.java)
                .apply { putExtra(SEMINAR_ID, id) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_seminar)
        val seminarId = intent.getIntExtra(SEMINAR_ID, -1)

        binding.run {
            viewModel = detailSeminarViewModel
            lifecycleOwner = this@DetailSeminarActivity
            instructorList.adapter = PersonListAdapter()
            instructorList.layoutManager = LinearLayoutManager(this@DetailSeminarActivity)
            participantList.adapter = PersonListAdapter()
            participantList.layoutManager = LinearLayoutManager(this@DetailSeminarActivity)
            participantButton.setOnClickListener {
                detailSeminarViewModel.enrollSeminar(seminarId, PARTICIPANT)
            }
            instructorButton.setOnClickListener {
                detailSeminarViewModel.enrollSeminar(seminarId, INSTRUCTOR)
            }
        }

        SingleEvent.triggerToast.observe(this, Observer { e ->
            e.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })

        detailSeminarViewModel.run {
            detailSeminar.value ?: getSeminarId(seminarId)
        }
    }
}