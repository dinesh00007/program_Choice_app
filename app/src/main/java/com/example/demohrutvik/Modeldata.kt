package com.example.demohrutvik

import androidx.room.Entity
import androidx.room.PrimaryKey


data class Modeldata(

    val Status: String,

    val StatusMessage: String,

    val InstructorStudentList: List<InstructorStudentList>,
)
@Entity(tableName = "user")
data class InstructorStudentList(


    @PrimaryKey(autoGenerate = false)
    val Id: Int,

    val StudentName: String,

    val StudentAddress: String,

    val CourseCode: String,

    val Phone: String,

    val RegistrationId: String,

    val PaymentStatus: String,

    val DueAmount: String,

    val DocumentDownloadLink: String,

    val BalanceAmount: String,

    val Score: String,

    val DrivingScore: String,

    val StudentPaymentType: String,

    val StudentDue: String,

    val StudentPaymentStatus: String,

    val Addons: String,

    val InstructorShare: String,

    val StudentType: String,

    val TotalLessonCount: String,

    val ExtraLessonType: String,

    val TotalPurchasedExtraLesson: String,

    val Gprice: String?,

    val G2Price: String,

    val TotalPrice: String,

    val PaidP1Amount: String,

    val PaidP2Amount: String,

    val Paidp3Amount: String,

    val ProgramName: String,

    val AdvanceAvailable: String,

    val IsEvaluationCompleted: String,

    val Is10LessonCompleted: String,

    val LessonId: String,

    val IsEvaluationeligible: String,

    val RoadTestaddonId: String,

    val IsRoadTestPurchased: String,

    val TotalExtraLessonCount: String,
)
