package com.android.example.seminarmanager.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.android.example.seminarmanager.R
import com.android.example.seminarmanager.databinding.ActivityMainBinding
import com.android.example.seminarmanager.ui.main.FragmentConst.SEMINAR
import com.android.example.seminarmanager.ui.main.FragmentConst.USER

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.run {
            pager.adapter = MainPagerAdapter(this@MainActivity)
            pager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    bottomNavigation.menu.getItem(position).isChecked = true
                }
            })
            bottomNavigation.setOnNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.page_1 -> pager.currentItem = SEMINAR
                    R.id.page_2 -> pager.currentItem = USER
                    else -> {throw Error("Not valid itemId in bottomNavigation")}
                }
                true
            }
        }
    }
}