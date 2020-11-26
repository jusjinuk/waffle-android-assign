package com.android.example.seminarmanager.ui.main

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.android.example.seminarmanager.R
import com.android.example.seminarmanager.databinding.ActivityMainBinding
import com.android.example.seminarmanager.di.NetworkConst.IS_INSTRUCTOR
import com.android.example.seminarmanager.di.NetworkConst.IS_IN_CHARGE
import com.android.example.seminarmanager.ui.SingleEvent.triggerMenuRefresh
import com.android.example.seminarmanager.ui.main.FragmentConst.SEMINAR
import com.android.example.seminarmanager.ui.main.FragmentConst.USER
import com.android.example.seminarmanager.ui.seminar.CreateSeminarActivity
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val prefs: SharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.run {
            pager.adapter = MainPagerAdapter(this@MainActivity)
            pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    bottomNavigation.menu.getItem(position).isChecked = true
                    invalidateOptionsMenu()
                }
            })
            bottomNavigation.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.page_1 -> pager.currentItem = SEMINAR
                    R.id.page_2 -> pager.currentItem = USER
                    else -> {
                        throw Error("Not valid itemId in bottomNavigation")
                    }
                }
                true
            }
        }
        triggerMenuRefresh.observe(this, Observer { e ->
            e.getContentIfNotHandled()?.let {
                invalidateOptionsMenu()
            }
        })
    }

    //https://stackoverflow.com/questions/28342181/show-menu-items-depending-viewpager-android

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.add_button_main_menu, menu)
        val bool = prefs.getString(IS_INSTRUCTOR, null) != null
        menu?.findItem(R.id.add_button)?.isVisible = binding.pager.currentItem == SEMINAR && bool
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id: Int = item.itemId
        return if (id == R.id.add_button) {
            //process your onClick here
            prefs.getString(IS_IN_CHARGE, null)?.let {
                startActivity(Intent(this, CreateSeminarActivity::class.java))
                finish()
            } ?: run {
                Toast.makeText(
                    this,
                    "하나의 세미나만 강의할 수 있습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            true
        } else super.onOptionsItemSelected(item)
    }
}