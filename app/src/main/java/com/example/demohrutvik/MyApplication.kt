package com.example.demohrutvik

import android.app.Application
import com.example.demohrutvik.local.AppDatabase

class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
     //   AppDatabase.getDatabase(this)
    }
}