package com.example.eduadmission.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "applications")
data class ApplicationEntity(
    @PrimaryKey(autoGenerate = true) val applicationId: Long = 0,
    val userId: String, // Kept as identifier
    val userEmail: String,
    val fullname: String,
    val universityId: String,
    val courseId: String,
    val universityName: String,
    val courseName: String,
    val status: String,         // Submitted, Under Review, Accepted, Rejected
    val submittedAt: Long,
    val documentUri: String? = null
)
