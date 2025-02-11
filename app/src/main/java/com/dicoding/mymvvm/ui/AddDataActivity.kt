package com.dicoding.mymvvm.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.mymvvm.data.local.entity.DesignTeam
import com.dicoding.mymvvm.databinding.ActivityAddDataBinding
import com.dicoding.mymvvm.viewmodel.DesignTeamViewModel
import com.dicoding.mymvvm.viewmodel.DesignTeamViewModelFactory

class AddDataActivity : AppCompatActivity() {

    // View binding for accessing UI elements
    private lateinit var binding: ActivityAddDataBinding

    // ViewModel instance for data operations
    private lateinit var viewModel: DesignTeamViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using view binding
        binding = ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up ViewModel using factory for dependency injection
        val factory = DesignTeamViewModelFactory(application)
        viewModel = ViewModelProvider(this, factory)[DesignTeamViewModel::class.java]

        // Handle save button click
        binding.btnSave.setOnClickListener {
            saveDesignTeamData()
        }
    }

    /**
     * Saves new DesignTeam data if input fields are valid.
     * Displays appropriate toast messages based on success or failure.
     */
    private fun saveDesignTeamData() {
        // Retrieve user input from text fields
        val name = binding.etName.text.toString().trim()
        val division = binding.etDivision.text.toString().trim()

        // Validate input fields
        if (name.isNotEmpty() && division.isNotEmpty()) {
            // Create a new DesignTeam object with the input data
            val newDesignTeam = DesignTeam(name = name, division = division)

            // Request ViewModel to insert the new data into the repository
            viewModel.insert(newDesignTeam)

            // Show success message and close the activity
            Toast.makeText(this, "Data added successfully!", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            // Show error message if fields are empty
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
        }
    }
}