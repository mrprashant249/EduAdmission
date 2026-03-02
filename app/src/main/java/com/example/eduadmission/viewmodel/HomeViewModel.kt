package com.example.eduadmission.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eduadmission.data.model.University
import com.example.eduadmission.data.repository.EduRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: EduRepository) : ViewModel() {

    private val _featuredUniversities = MutableLiveData<List<University>>()
    val featuredUniversities: LiveData<List<University>> = _featuredUniversities

    fun loadFeatured() {
        viewModelScope.launch {
            _featuredUniversities.value = repository.getUniversities().take(3)
        }
    }
}
