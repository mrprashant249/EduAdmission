package com.example.eduadmission.data.repository

import com.example.eduadmission.data.local.ApplicationEntity
import com.example.eduadmission.data.local.EduDao
import com.example.eduadmission.data.local.UserEntity
import com.example.eduadmission.data.model.Course
import com.example.eduadmission.data.model.University
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.delay

class EduRepository(private val eduDao: EduDao) {

    suspend fun getUniversities(): List<University> {
        delay(100)
        return FakeDataSource.universities
    }

    fun getUniversityById(id: String): University? {
        return FakeDataSource.universities.find { it.id == id }
    }

    suspend fun getCourses(universityId: String): List<Course> {
        delay(100)
        return FakeDataSource.courses.filter { it.universityId == universityId }
    }
    
    fun getCourseById(courseId: String): Course? {
        return FakeDataSource.courses.find { it.id == courseId }
    }

    // Local DB Operations
    suspend fun login(email: String): UserEntity? {
         return eduDao.getUser(email)
    }

    suspend fun register(user: UserEntity) {
        eduDao.insertUser(user)
    }

    suspend fun submitApplication(application: ApplicationEntity): Long {
        return eduDao.insertApplication(application)
    }
    
    fun getMyApplications(userId: String): Flow<List<ApplicationEntity>> {
        return eduDao.getApplicationsForUser(userId)
    }

    suspend fun updateApplicationStatus(appId: Long, status: String) {
        eduDao.updateApplicationStatus(appId, status)
    }

    suspend fun deleteApplication(appId: Long) {
        eduDao.deleteApplication(appId)
    }
}
