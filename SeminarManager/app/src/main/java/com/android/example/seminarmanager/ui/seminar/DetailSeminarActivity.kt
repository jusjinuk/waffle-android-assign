package com.android.example.seminarmanager.ui.seminar

import android.app.Person
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.example.seminarmanager.R
import com.android.example.seminarmanager.databinding.ActivityDetailSeminarBinding
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
        }

        detailSeminarViewModel.run {
            detailSeminar.value ?: getSeminarId(seminarId)
        }
    }
}