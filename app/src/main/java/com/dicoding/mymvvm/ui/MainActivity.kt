package com.dicoding.mymvvm.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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
    private lateinit var adapter: DesignTeamAdapter

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
        initAddDataButton() // Navigate to the Add Page
        initDeleteAllButton() // Delete all data
    }

    private fun initAdapter() {
        adapter = DesignTeamAdapter()
        binding.rvDesignTeam.adapter = adapter

        // Handle delete click
        adapter.setOnItemDeleteClickListener { designTeam ->
            AlertDialog.Builder(this)
                .setTitle("Delete All")
                .setMessage("Are you sure you want to delete all records?")
                .setPositiveButton("Yes") { _, _ ->
                    viewModel.delete(designTeam)
                    Toast.makeText(this, "${designTeam.name} deleted!", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No", null)
                .show()
        }

        adapter.setOnItemEditClickListener { designTeam ->
            val intent = Intent(this, EditDataActivity::class.java).apply {
                putExtra("EXTRA_ID", designTeam.id)
                putExtra("EXTRA_NAME", designTeam.name)
                putExtra("EXTRA_DIVISION", designTeam.division)
            }
            startActivity(intent)
        }
    }

    private fun initViewModel() {
        viewModel.getDesignTeam()
    }

    private fun initDesignTeam() {
        viewModel.getDesignTeam.observe(this) { result ->
            adapter.differ.submitList(result)
        }
    }

    private fun initInsertDesignTeam() {
        val designTeam = listOf(
            DesignTeam(name = "Rizka Ghinna", division = "UX Engineer"),
            DesignTeam(name = "Shidiq Bagus", division = "UX Engineer")
        )

        designTeam.forEach {
            viewModel.insert(it)
        }
    }

    private fun initAddDataButton() {
        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, AddDataActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initDeleteAllButton() {
        binding.btnDeleteAll.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Delete All")
                .setMessage("Are you sure you want to delete all records?")
                .setPositiveButton("Yes") { _, _ ->
                    viewModel.deleteAllDesignTeam()
                    Toast.makeText(this, "All data deleted!", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No", null)
                .show()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getDesignTeam()
    }
}