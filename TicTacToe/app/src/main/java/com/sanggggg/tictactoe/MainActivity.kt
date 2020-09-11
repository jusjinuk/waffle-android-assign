package com.sanggggg.tictactoe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sanggggg.tictactoe.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

// TODO: Complete MainActivity.kt
// You may have to add some onClick listeners for buttons

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var ticTacToeViewModel: TicTacToeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ticTacToeViewModel = ViewModelProvider(this).get(TicTacToeViewModel::class.java)

        binding.apply {
            viewModel = ticTacToeViewModel
            lifecycleOwner = this@MainActivity

            button0.setOnClickListener{
                ticTacToeViewModel.clickCell(0)
            }
            button1.setOnClickListener{
                ticTacToeViewModel.clickCell(1)
            }
            button2.setOnClickListener{
                ticTacToeViewModel.clickCell(2)
            }
            button3.setOnClickListener{
                ticTacToeViewModel.clickCell(3)
            }
            button4.setOnClickListener{
                ticTacToeViewModel.clickCell(4)
            }
            button5.setOnClickListener{
                ticTacToeViewModel.clickCell(5)
            }
            button6.setOnClickListener{
                ticTacToeViewModel.clickCell(6)
            }
            button7.setOnClickListener{
                ticTacToeViewModel.clickCell(7)
            }
            button8.setOnClickListener{
                ticTacToeViewModel.clickCell(8)
            }
            buttonRestart.setOnClickListener{
                ticTacToeViewModel.restart()
            }

        }
    }
}