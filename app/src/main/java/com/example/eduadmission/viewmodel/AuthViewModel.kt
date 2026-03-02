package com.example.eduadmission.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eduadmission.data.local.UserEntity
import com.example.eduadmission.data.repository.EduRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: EduRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    private val _currentUser = MutableLiveData<UserEntity?>()
    val currentUser: LiveData<UserEntity?> = _currentUser

    fun login(email: String, name: String) {
        viewModelScope.launch {
            var user = repository.login(email)
            if (user == null) {
                user = UserEntity(email, name, "password")
                repository.register(user)
            }
            _currentUser.value = user
            _loginResult.value = true
        }
    }
}
