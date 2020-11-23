package com.android.example.seminarmanager.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.android.example.seminarmanager.databinding.ItemSeminarBinding
import com.android.example.seminarmanager.databinding.ItemSeminarInstructingBinding
import com.android.example.seminarmanager.databinding.ItemSeminarParticipatingBinding
import com.android.example.seminarmanager.network.dto.SimpleSeminarDto
import com.android.example.seminarmanager.ui.seminar.DetailSeminarActivity

class SeminarViewHolder(private val binding: ItemSeminarBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindItems(seminar: SimpleSeminarDto) {

        binding.run {
            var tmp = ""
            seminarName.text = seminar.name
            seminar.instructors.forEach {
                tmp = if (tmp.isEmpty())
                    it.username
                else
                    tmp + ", " + it.username
            }
            instructorName.text = tmp
            clickListener = SeminarClickListener(seminar.id) {
                binding.root.context.startActivity(
                    DetailSeminarActivity.intentWithSeminarId(binding.root.context, it)
                )
            }
        }
    }
}

class SeminarParticipatingViewHolder(private val binding: ItemSeminarParticipatingBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindItems(seminar: SimpleSeminarDto) {
        binding.run {
            var tmp = ""
            seminarName.text = seminar.name
            seminar.instructors.forEach {
                tmp = if (tmp.isEmpty())
                    it.username
                else
                    tmp + ", " + it.username
            }
            instructorName.text = tmp
        }
    }
}

class SeminarInstructingViewHolder(private val binding: ItemSeminarInstructingBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindItems(seminar: SimpleSeminarDto) {
        binding.run {
            var tmp = ""
            seminarName.text = seminar.name
            seminar.instructors.forEach {
                tmp = if (tmp.isEmpty())
                    it.username
                else
                    tmp + ", " + it.username
            }
            instructorName.text = tmp
        }
    }
}

class SeminarClickListener(private val seminar_id: Int, val clickListener: (id: Int) -> Unit) {
    fun onClick() = clickListener(seminar_id)
}