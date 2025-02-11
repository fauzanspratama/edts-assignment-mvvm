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

    // View binding instance to access UI components without findViewById
    private lateinit var binding: ActivityEditDataBinding

    // ViewModel instance to handle data operations and business logic
    private lateinit var viewModel: DesignTeamViewModel

    // Stores the ID of the design team being edited
    private var designTeamId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using view binding
        binding = ActivityEditDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel using a factory for dependency injection
        val factory = DesignTeamViewModelFactory(application)
        viewModel = ViewModelProvider(this, factory)[DesignTeamViewModel::class.java]

        // Retrieve data passed through the intent and populate the input fields
        val name = intent.getStringExtra("EXTRA_NAME")
        val division = intent.getStringExtra("EXTRA_DIVISION")
        designTeamId = intent.getIntExtra("EXTRA_ID", 0)

        // Pre-fill the input fields with existing data
        binding.etName.setText(name)
        binding.etDivision.setText(division)

        // Set up listener for the Update button
        binding.btnUpdate.setOnClickListener {
            updateDesignTeam() // Call method to handle data update
        }
    }

    /**
     * Updates the DesignTeam data if input fields are valid.
     * Displays a toast message based on success or validation failure.
     */
    private fun updateDesignTeam() {
        // Retrieve user input from the text fields
        val updatedName = binding.etName.text.toString().trim()
        val updatedDivision = binding.etDivision.text.toString().trim()

        // Check if both fields are filled
        if (updatedName.isNotEmpty() && updatedDivision.isNotEmpty()) {
            // Create a DesignTeam object with the updated data
            val updatedDesignTeam = DesignTeam(
                id = designTeamId,
                name = updatedName,
                division = updatedDivision
            )

            // Request ViewModel to update the data in the repository
            viewModel.update(updatedDesignTeam)

            // Show success message and close the activity
            Toast.makeText(this, "Data updated successfully!", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            // Show error message if any field is empty
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
        }
    }
}