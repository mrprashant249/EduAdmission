package com.example.eduadmission.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EduDao {
    // User
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUser(email: String): UserEntity?

    @Insert
    suspend fun insertApplication(application: ApplicationEntity): Long

    @Query("SELECT * FROM applications WHERE userId = :userId ORDER BY submittedAt DESC")
    fun getApplicationsForUser(userId: String): Flow<List<ApplicationEntity>>

    @Query("UPDATE applications SET status = :status WHERE applicationId = :appId")
    suspend fun updateApplicationStatus(appId: Long, status: String)

    @Query("DELETE FROM applications WHERE applicationId = :appId")
    suspend fun deleteApplication(appId: Long)
}
