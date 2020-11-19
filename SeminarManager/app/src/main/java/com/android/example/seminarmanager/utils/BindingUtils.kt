package com.android.example.seminarmanager.utils

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.example.seminarmanager.network.dto.SimpleSeminarDto
import com.android.example.seminarmanager.network.dto.UserDto
import com.android.example.seminarmanager.ui.main.seminar.SeminarListAdapter

@BindingAdapter(value = ["items", "user"], requireAll = false)
fun bindSeminarItems(view: RecyclerView, items: List<SimpleSeminarDto>?, user: UserDto?) {
    view.adapter = SeminarListAdapter(user)
    val adapt = view.adapter as SeminarListAdapter
    if(items != null){
        adapt.submitList(items)
    }
}