package com.example.demohrutvik.local
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.demohrutvik.InstructorStudentList

@Database(entities = [InstructorStudentList::class],version=1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao():UserDao

    companion object {
        @Volatile
        var INSTANCES: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase {
//            return INSTANCES : synchronized(this) {
            INSTANCES=   Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_demo_database"
                ).allowMainThreadQueries().build()
            return INSTANCES!!
//            }
        }
    }
}

