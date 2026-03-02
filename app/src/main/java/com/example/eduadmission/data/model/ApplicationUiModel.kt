package com.example.eduadmission.data.model

data class ApplicationUiModel(
    val applicationId: Long,
    val userId: String,
    val userEmail: String,
    val fullname: String,
    val universityId: String,
    val courseId: String,
    val universityName: String,
    val courseName: String,
    val status: String,
    val submittedAt: Long
)
