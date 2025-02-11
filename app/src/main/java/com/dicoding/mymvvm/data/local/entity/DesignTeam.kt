package com.dicoding.mymvvm.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// @Entity marks this data class as a table in the Room database.
// The "tableName" parameter specifies the name of the table in the database.
@Entity(tableName = "design_team")
data class DesignTeam (
    // @PrimaryKey is used to designate the primary key for the table.
    // "autoGenerate = true" allows Room to automatically generate unique IDs.
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // "name" column to store the name of the design team member.
    val name: String = "",

    // "division" column to store the division to which the member belongs.
    val division: String = ""
)