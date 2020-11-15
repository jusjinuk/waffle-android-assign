package com.android.example.seminarmanager.ui.main.seminar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.example.seminarmanager.R

class SeminarFragment : Fragment() {

    companion object {
        fun newInstance() = SeminarFragment()
    }

    private lateinit var viewModel: SeminarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.seminar_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SeminarViewModel::class.java)
        // TODO: Use the ViewModel
    }

}