package com.example.tsechacksapp.data

data class PatientDetail(var desc: String?="",
                         var status : String?="false" )

data class PatientDetailList(var Fields: MutableList<PatientDetail>? = mutableListOf())