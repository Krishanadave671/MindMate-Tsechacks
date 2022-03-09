package com.example.tsechacksapp.doctor

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tsechacksapp.Adapter.PatientDetailsAdapter
import com.example.tsechacksapp.R
import com.example.tsechacksapp.data.PatientDetail
import com.example.tsechacksapp.data.PatientDetailList
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class PatientInfo : AppCompatActivity() {

    private lateinit var patientDetailRecyclerView: RecyclerView
    private lateinit var firebaseStore: FirebaseStorage
    private lateinit var storageReference: StorageReference
    private lateinit var patientInfoList: ArrayList<PatientDetail>
    private lateinit var patientDetailsAdapter : PatientDetailsAdapter
    var patientname: String? = "Patient1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_info)
        patientDetailRecyclerView = findViewById(R.id.patient_info_recyclerview)
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference


        patientDetailRecyclerView.layoutManager = LinearLayoutManager(this)
        patientDetailRecyclerView.setHasFixedSize(true)
        patientInfoList = arrayListOf()

        patientDetailsAdapter = PatientDetailsAdapter(patientInfoList)
        patientDetailRecyclerView.adapter = patientDetailsAdapter

        EventChangeListener()






    }

    @SuppressLint("NotifyDataSetChanged")
    private fun EventChangeListener() {
        val db =FirebaseFirestore.getInstance().collection("patients")
            .document("patient1")
            .collection("medicalRecords")
            .document("history")
            .get()
            .addOnSuccessListener { documentSnapshot ->
                val city = documentSnapshot.toObject(PatientDetailList::class.java)
                for(i in city?.Fields!!){
                    patientInfoList.add(i)
                    Log.e("TG",i.toString())
                    patientDetailsAdapter.notifyDataSetChanged()
                }
                Log.e("TG",city.toString())
            }
        patientDetailsAdapter.notifyDataSetChanged()

    }
}