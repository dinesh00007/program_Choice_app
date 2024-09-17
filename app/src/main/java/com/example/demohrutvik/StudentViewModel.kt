package com.example.demohrutvik

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import android.util.Log
import android.widget.ViewSwitcher.ViewFactory
import androidx.lifecycle.ViewModelProvider
import com.example.demohrutvik.local.AppDatabase
import com.example.demohrutvik.local.UserDao
import okhttp3.internal.userAgent

class StudentViewModel(private val repository: StudentRepository) : ViewModel() {

    private val _students = MutableLiveData<List<InstructorStudentList>>()
    val students: LiveData<List<InstructorStudentList>> = _students

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private var currentPage = 1
    var isLoading = false

    fun fetchStudents() {
        if (isLoading) return
        isLoading = true

        val params = JsonObject().apply {
            addProperty("InstructorId", "1662")
            addProperty("LocationId", "1")
            addProperty("Year", "2024")
            addProperty("StudentPayType", "ALL")
            addProperty("StudentPaymentType", "")
            addProperty("StudentType", "")
            addProperty("SearchTitle", "")
            addProperty("SPageNo", currentPage.toString())
            addProperty("EvaluationType", "")
        }

        repository.getInstructorStudentList(params, { response ->
            isLoading = false
            response?.let {
                if (it.InstructorStudentList.isNullOrEmpty()) {
                    _error.value = "No more students found"
                    Log.d("StudentViewModel", "No more students found")
                } else {
               //     if (!isNetworkAvailable(context = Application())) {
                        _error.value = "No network available"
                        Log.e("StudentViewModel", "Network not available")
                        currentPage++
                        Log.d("StudentViewModel", "Students fetched: ${it.InstructorStudentList.size}")

                        _students.value = AppDatabase.INSTANCES?.userDao()!!.getUser()
                    savadatauser(response.InstructorStudentList)
                        return@getInstructorStudentList
//                    }
//                    else
//                    {
//                        _students.value = it.InstructorStudentList
//                        savadatauser(response.InstructorStudentList)
//                        currentPage++
//                    //}

//                    val updatedList = _students.value?.toMutableList() ?: mutableListOf()
//                    updatedList.addAll(it.InstructorStudentList)


                    Log.d("StudentViewModel", "Students fetched: ${it.InstructorStudentList.size}")
//
//                    _students.value = AppDatabase.INSTANCES?.userDao()!!.getUser()
//                    Log.d("@DB",AppDatabase.INSTANCES?.userDao()!!.getUser().toString())
                }
            } ?: run {
                _error.value = "Empty response"
                Log.e("StudentViewModel", "Received empty response")
            }
        }, { throwable ->
            isLoading = false
            _error.value = throwable?.message ?: "Unknown Error"
            Log.e("StudentViewModel", "Error fetching students", throwable)
        })
    }

    fun savadatauser(userModel: List<InstructorStudentList>){
        AppDatabase.INSTANCES?.let {
            it.userDao().insert(userModel)
        }

    }
}

class StudentViewmodelFactory(private val repository: StudentRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StudentViewModel(repository) as T
    }
}