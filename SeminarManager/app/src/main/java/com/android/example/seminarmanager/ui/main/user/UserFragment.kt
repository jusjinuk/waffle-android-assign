package com.android.example.seminarmanager.ui.main.user

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.android.example.seminarmanager.R
import com.android.example.seminarmanager.databinding.UserFragmentBinding
import com.android.example.seminarmanager.ui.SingleEvent
import org.koin.android.viewmodel.ext.android.viewModel

class UserFragment : Fragment() {

    private val userViewModel: UserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<UserFragmentBinding>(
            inflater,
            R.layout.user_fragment, container, false
        )

        binding.run {
            viewModel = userViewModel
            lifecycleOwner = viewLifecycleOwner
            paricipatingSeminarList.adapter = SeminarHorizontalListAdapter()
            instructingSeminarList.adapter = SeminarHorizontalListAdapter()

            editButton.setOnClickListener {
                userViewModel.putUserMe()
                // Hide the keyboard.
                val inputMethodManager =
                    activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
                usernameInput.clearFocus()
                firstNameInput.clearFocus()
                lastNameInput.clearFocus()
            }
        }

        SingleEvent.triggerToast.observe(this, Observer { e ->
            e.getContentIfNotHandled()?.let {
                Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            }
        })
        if (userViewModel.user.value == null)
            userViewModel.getUserMe()

        return binding.root
    }

}