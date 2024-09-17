package com.example.demohrutvik

import android.util.Log
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class StudentRepository() {
    fun getInstructorStudentList(
        params: JsonObject,
        onSuccess: (Modeldata?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        RetrofitInstance.apiService.getInstructorStudentList(json = params).enqueue(object : Callback<Modeldata> {
            override fun onResponse(call: Call<Modeldata>, response: Response<Modeldata>) {
                if (response.isSuccessful) {
                    onSuccess(response.body())
                    Log.d("res",response.body().toString())
                } else {
                    onError(Exception("Error: ${response.message()}"))
                }
            }

            override fun onFailure(call: Call<Modeldata>, t: Throwable) {
                onError(t)
            }
        })
    }
}
