package com.dicoding.mymvvm.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.dicoding.mymvvm.R
import com.dicoding.mymvvm.data.local.entity.DesignTeam
import com.dicoding.mymvvm.databinding.ActivityMainBinding
import com.dicoding.mymvvm.viewmodel.DesignTeamViewModel
import com.dicoding.mymvvm.viewmodel.DesignTeamViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: DesignTeamViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val factory = DesignTeamViewModelFactory(application)
        viewModel = ViewModelProvider(this, factory)[DesignTeamViewModel::class.java]

        initAdapter()
        initViewModel()
        initDesignTeam()
        initInsertDesignTeam()
    }


    private fun initViewModel() {
        viewModel.getDesignTeam()
    }


    private fun initAdapter() {
        val adapter = DesignTeamAdapter()
        binding.rvDesignTeam.adapter = adapter
    }

    private fun initDesignTeam() {
        viewModel.getDesignTeam.observe(this) { result ->
            val adapter = binding.rvDesignTeam.adapter as DesignTeamAdapter
            adapter.differ.submitList(result)
        }
    }


    private fun initInsertDesignTeam() {
        val DesignTeam = listOf(
            DesignTeam(
                name = "Rizka Ghinna",
                division = "UX Engineer"
            ),
            DesignTeam(
                name = "Shidiq Bagus",
                division = "UX Engineer"
            )
        )

        DesignTeam.forEach {
            viewModel.insert(it)
        }
    }
}
