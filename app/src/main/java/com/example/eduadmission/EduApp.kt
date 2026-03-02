package com.example.eduadmission

import android.app.Application
import com.example.eduadmission.data.local.EduDatabase
import com.example.eduadmission.data.repository.EduRepository

class EduApp : Application() {
    
    val database by lazy { EduDatabase.getDatabase(this) }
    val repository by lazy { EduRepository(database.eduDao()) }

    override fun onCreate() {
        super.onCreate()
    }
}
