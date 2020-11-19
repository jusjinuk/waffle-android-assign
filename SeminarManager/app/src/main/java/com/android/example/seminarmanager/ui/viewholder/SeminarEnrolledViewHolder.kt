package com.android.example.seminarmanager.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.android.example.seminarmanager.databinding.ItemSeminarEnrolledBinding
import com.android.example.seminarmanager.network.dto.SeminarEnrolled

class SeminarEnrolledViewHolder(private val binding: ItemSeminarEnrolledBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindItems(seminar: SeminarEnrolled) {
        binding.run {
            seminarName.text = seminar.name
        }
    }
}