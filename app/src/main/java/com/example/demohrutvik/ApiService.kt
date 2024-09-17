package com.example.demohrutvik

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface ApiService {
    @POST("InstructorStudentList") // Ensure this is the correct endpoint relative to your base URL
    fun getInstructorStudentList(
        @Header("HashToken") key: String = "e9a9b11e-9630-486e-af79-7e0fa3d81278", // Default token, adjust as needed
        @Body json: JsonObject // Ensure this matches the expected request body structure
    ): Call<Modeldata> // Ensure Modeldata matches the expected response structure
}