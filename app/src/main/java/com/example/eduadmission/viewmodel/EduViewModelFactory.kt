package com.example.eduadmission.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eduadmission.data.repository.EduRepository

class EduViewModelFactory(private val repository: EduRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(UniversityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UniversityViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(ApplicationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ApplicationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
