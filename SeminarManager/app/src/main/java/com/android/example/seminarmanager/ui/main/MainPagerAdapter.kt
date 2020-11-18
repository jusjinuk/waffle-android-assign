package com.android.example.seminarmanager.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.example.seminarmanager.ui.main.FragmentConst.SEMINAR
import com.android.example.seminarmanager.ui.main.FragmentConst.USER
import com.android.example.seminarmanager.ui.main.seminar.SeminarFragment
import com.android.example.seminarmanager.ui.main.user.UserFragment
import java.lang.Error

class MainPagerAdapter(activity : FragmentActivity) :FragmentStateAdapter(activity){
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            SEMINAR -> SeminarFragment()
            USER -> UserFragment()
            else -> {
                throw Error("Not valid fragment for designated page number.")
            }
        }
    }
}

object FragmentConst {
    const val SEMINAR = 0
    const val USER = 1
}