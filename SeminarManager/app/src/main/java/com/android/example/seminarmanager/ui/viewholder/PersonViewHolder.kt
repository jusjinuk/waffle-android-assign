package com.android.example.seminarmanager.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.android.example.seminarmanager.databinding.ItemPersonBinding
import com.android.example.seminarmanager.databinding.ItemSeminarEnrolledBinding
import com.android.example.seminarmanager.network.dto.SeminarEnrolled
import com.android.example.seminarmanager.network.dto.SeminarInstructor
import com.android.example.seminarmanager.network.dto.SeminarParticipant
import com.android.example.seminarmanager.network.dto.SeminarPerson

class PersonViewHolder(private val binding: ItemPersonBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindItems(person: SeminarPerson) {
        binding.run {
            when (person) {
                is SeminarInstructor -> {
                    personName.text = person.username
                }
                is SeminarParticipant -> {
                    personName.text = person.username
                }
            }
        }
    }
}