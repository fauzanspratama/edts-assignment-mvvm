package com.dicoding.mymvvm.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.mymvvm.data.local.entity.DesignTeam
import com.dicoding.mymvvm.databinding.ActivityEditDataBinding
import com.dicoding.mymvvm.viewmodel.DesignTeamViewModel
import com.dicoding.mymvvm.viewmodel.DesignTeamViewModelFactory

class EditDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditDataBinding
    private lateinit var viewModel: DesignTeamViewModel
    private var designTeamId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = DesignTeamViewModelFactory(application)
        viewModel = ViewModelProvider(this, factory)[DesignTeamViewModel::class.java]

        // Retrieve data from intent
        val name = intent.getStringExtra("EXTRA_NAME")
        val division = intent.getStringExtra("EXTRA_DIVISION")
        designTeamId = intent.getIntExtra("EXTRA_ID", 0)

        binding.etName.setText(name)
        binding.etDivision.setText(division)

        binding.btnUpdate.setOnClickListener {
            updateDesignTeam()
        }
    }

    private fun updateDesignTeam() {
        val updatedName = binding.etName.text.toString().trim()
        val updatedDivision = binding.etDivision.text.toString().trim()

        if (updatedName.isNotEmpty() && updatedDivision.isNotEmpty()) {
            val updatedDesignTeam = DesignTeam(
                id = designTeamId,
                name = updatedName,
                division = updatedDivision
            )
            viewModel.update(updatedDesignTeam)
            Toast.makeText(this, "Data updated successfully!", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
        }
    }
}
