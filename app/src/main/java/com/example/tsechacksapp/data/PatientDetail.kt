package com.example.tsechacksapp.data

data class PatientDetail(var desc: String?="",
                         var status : String?="false" )

data class PatientDetailList(var Fields: MutableList<PatientDetail>? = mutableListOf())



data class MedicineDetail(var name: String?="", var morning: Boolean? = false, var evening: Boolean? = false,
        var night: Boolean?=false, var quantity: String?="0 pills")