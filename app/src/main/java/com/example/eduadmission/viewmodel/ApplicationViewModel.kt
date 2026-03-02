package com.example.eduadmission.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.eduadmission.data.local.ApplicationEntity
import com.example.eduadmission.data.model.ApplicationUiModel
import com.example.eduadmission.data.repository.EduRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch

class ApplicationViewModel(private val repository: EduRepository) : ViewModel() {

    fun getApplications(userId: String): LiveData<List<ApplicationUiModel>> {
        return repository.getMyApplications(userId).transform { apps ->
            val universities = repository.getUniversities()
            val uiModels = apps.map { app ->
                val uni = universities.find { it.id == app.universityId }
                // Assuming getCourseById is available or fetches from local list
                // If getCourseById is suspend, we might need to handle it.
                // For now, let's assume we can fetch course. 
                // Wait, repository.getCourseById(app.courseId) might be suspend? Yes.
                // Doing this in a loop is bad, but for MVP/small list is ok.
                val course = repository.getCourseById(app.courseId)

                ApplicationUiModel(
                    applicationId = app.applicationId,
                    userId = app.userId,
                    userEmail = app.userEmail,
                    fullname = app.fullname,
                    universityId = app.universityId,
                    courseId = app.courseId,
                    universityName = uni?.name ?: "Unknown University",
                    courseName = course?.title ?: "Unknown Course",
                    status = app.status,
                    submittedAt = app.submittedAt
                )
            }
            emit(uiModels)
        }.asLiveData()
    }

    fun submitApplication(app: ApplicationEntity) {
        viewModelScope.launch {
            val appId = repository.submitApplication(app)
            
            // Auto update status after 5 seconds to simulate review
            delay(5000)
            repository.updateApplicationStatus(appId, "Under Review")
        }
    }
    fun updateApplicationStatus(appId: Long, status: String) {
        viewModelScope.launch {
            repository.updateApplicationStatus(appId, status)
        }
    }

    fun deleteApplication(appId: Long) {
        viewModelScope.launch {
            repository.deleteApplication(appId)
        }
    }
}
