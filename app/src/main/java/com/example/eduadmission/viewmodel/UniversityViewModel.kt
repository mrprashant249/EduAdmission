package com.example.eduadmission.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eduadmission.data.model.Course
import com.example.eduadmission.data.model.University
import com.example.eduadmission.data.repository.EduRepository
import kotlinx.coroutines.launch

class UniversityViewModel(private val repository: EduRepository) : ViewModel() {

    private val _universities = MutableLiveData<List<University>>()
    val universities: LiveData<List<University>> = _universities

    private val _courses = MutableLiveData<List<Course>>()
    val courses: LiveData<List<Course>> = _courses

    fun loadUniversities() {
        viewModelScope.launch {
            _universities.value = repository.getUniversities()
        }
    }

    fun loadCourses(universityId: String) {
        viewModelScope.launch {
            _courses.value = repository.getCourses(universityId)
        }
    }

    private val _selectedCourse = MutableLiveData<Course>()
    val selectedCourse: LiveData<Course> = _selectedCourse

    fun selectCourse(courseId: String) {
        viewModelScope.launch {
            _selectedCourse.value = repository.getCourseById(courseId)
        }
    }

    private val _selectedUniversity = MutableLiveData<University>()
    val selectedUniversity: LiveData<University> = _selectedUniversity

    fun loadUniversity(universityId: String) {
        viewModelScope.launch {
            _selectedUniversity.value = repository.getUniversityById(universityId)
        }
    }
}
