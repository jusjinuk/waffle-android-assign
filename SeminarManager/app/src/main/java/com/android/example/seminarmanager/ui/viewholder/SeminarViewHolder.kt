package com.android.example.seminarmanager.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.android.example.seminarmanager.databinding.ItemSeminarBinding
import com.android.example.seminarmanager.databinding.ItemSeminarInstructingBinding
import com.android.example.seminarmanager.databinding.ItemSeminarParticipatingBinding
import com.android.example.seminarmanager.network.dto.SimpleSeminarDto

class SeminarViewHolder(private val binding: ItemSeminarBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindItems(seminar: SimpleSeminarDto) {
        binding.run {
            seminarName.text = seminar.name
            seminar.instructors.forEach {
                if (instructorName.text.isEmpty())
                    instructorName.text = it.username
                else
                    instructorName.append(", " + it.username)
            }
        }
    }
}

class SeminarParticipatingViewHolder(private val binding: ItemSeminarParticipatingBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindItems(seminar: SimpleSeminarDto) {
        binding.run {
            seminarName.text = seminar.name
            seminar.instructors.forEach {
                if (instructorName.text.isEmpty())
                    instructorName.text = it.username
                else
                    instructorName.append(", " + it.username)
            }
        }
    }
}

class SeminarInstructingViewHolder(private val binding: ItemSeminarInstructingBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindItems(seminar: SimpleSeminarDto) {
        binding.run {
            seminarName.text = seminar.name
            seminar.instructors.forEach {
                if (instructorName.text.isEmpty())
                    instructorName.text = it.username
                else
                    instructorName.append(", " + it.username)
            }
        }
    }
}
