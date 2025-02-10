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

    private lateinit var binding: ActivityAddDataBinding
    private lateinit var viewModel: DesignTeamViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = DesignTeamViewModelFactory(application)
        viewModel = ViewModelProvider(this, factory)[DesignTeamViewModel::class.java]

        binding.btnSave.setOnClickListener {
            saveDesignTeamData()
        }
    }

    private fun saveDesignTeamData() {
        val name = binding.etName.text.toString().trim()
        val division = binding.etDivision.text.toString().trim()

        if (name.isNotEmpty() && division.isNotEmpty()) {
            val newDesignTeam = DesignTeam(name = name, division = division)
            viewModel.insert(newDesignTeam)
            Toast.makeText(this, "Data added successfully!", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
        }
    }
}
