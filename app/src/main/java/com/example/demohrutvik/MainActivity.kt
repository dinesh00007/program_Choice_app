package com.example.demohrutvik

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.demohrutvik.databinding.ActivityMainBinding
import com.example.demohrutvik.local.AppDatabase

class MainActivity : AppCompatActivity() {


    private lateinit var b: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the binding
        b = ActivityMainBinding.inflate(layoutInflater)

      //


        // Set the content view using the binding
        setContentView(b.root)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
  AppDatabase.getDatabase(this)
        b.btnclick.setOnClickListener {
            b.btnclick.visibility=View.GONE

            replaceFragment(StudentListFragment())
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}
