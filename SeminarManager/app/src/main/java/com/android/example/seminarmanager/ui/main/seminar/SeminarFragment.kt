package com.android.example.seminarmanager.ui.main.seminar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.example.seminarmanager.R
import com.android.example.seminarmanager.databinding.SeminarFragmentBinding
import com.android.example.seminarmanager.ui.SingleEvent.triggerToast
import org.koin.android.viewmodel.ext.android.viewModel

class SeminarFragment : Fragment() {

    private val seminarViewModel: SeminarViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<SeminarFragmentBinding>(inflater,
            R.layout.seminar_fragment, container, false)

        binding.run {
            viewModel = seminarViewModel
            lifecycleOwner = viewLifecycleOwner
            seminarList.layoutManager = LinearLayoutManager(activity)
            seminarList.adapter = SeminarListAdapter(seminarViewModel.user.value)
        }

        triggerToast.observe(this, Observer { e ->
            e.getContentIfNotHandled()?.let {
                Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        seminarViewModel.getSeminarList()
    }

}