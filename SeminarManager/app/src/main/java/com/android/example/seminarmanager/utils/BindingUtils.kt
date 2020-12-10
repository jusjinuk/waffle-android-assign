package com.android.example.seminarmanager.utils

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.example.seminarmanager.network.dto.SeminarEnrolled
import com.android.example.seminarmanager.network.dto.SeminarPerson
import com.android.example.seminarmanager.network.dto.SimpleSeminarDto
import com.android.example.seminarmanager.network.dto.UserDto
import com.android.example.seminarmanager.ui.main.seminar.SeminarListAdapter
import com.android.example.seminarmanager.ui.main.user.SeminarHorizontalListAdapter
import com.android.example.seminarmanager.ui.seminar.PersonListAdapter

@BindingAdapter(value = ["seminars", "user"], requireAll = false)
fun bindSeminarItems(view: RecyclerView, items: List<SimpleSeminarDto>?, user: UserDto?) {
    view.adapter = SeminarListAdapter(user)
    val adapt = view.adapter as SeminarListAdapter
    if(items != null){
        adapt.submitList(items)
    }
}

@BindingAdapter("participating_seminars_enrolled")
fun bindSeminarEnrolledItems(view: RecyclerView, items: List<SeminarEnrolled>?) {
    val adapt = view.adapter as SeminarHorizontalListAdapter
    if(items != null){
        adapt.submitList(items)
    }
}

@BindingAdapter("instructing_seminar_enrolled")
fun bindSeminarEnrolledItem(view: RecyclerView, item: SeminarEnrolled?) {
    val adapt = view.adapter as SeminarHorizontalListAdapter
    if(item != null){
        adapt.submitList(listOf(item))
    }
}

@BindingAdapter("people_seminar")
fun bindPeopleItems(view: RecyclerView, items: List<SeminarPerson>?) {
    val adapt = view.adapter as PersonListAdapter
    if(items != null){
        adapt.submitList(items)
    }
}