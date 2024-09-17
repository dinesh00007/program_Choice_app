package com.example.demohrutvik.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.demohrutvik.InstructorStudentList
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
     fun getUser():List<InstructorStudentList>

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(userModel:List<InstructorStudentList>)



}