package com.example.tsechacksapp.doctor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.example.tsechacksapp.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class AddMedicine : AppCompatActivity() {



    private lateinit var firebaseStore: FirebaseStorage
    private lateinit var storageReference: StorageReference
    private lateinit var chkm: CheckBox
    private lateinit var chke: CheckBox
    private lateinit var chkn: CheckBox
    private lateinit var submitBtn: Button
    private lateinit var medicineName: TextView
    private lateinit var medicineDesc: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_medicine)

        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        chkm = findViewById(R.id.checkBox_medicine_m)
        chke = findViewById(R.id.checkBox_medicine_e)
        chkn = findViewById(R.id.checkBox_medicine_n)
        medicineName = findViewById(R.id.medicine_name_add)
        submitBtn = findViewById(R.id.button_add_medicine)
        medicineDesc = findViewById(R.id.medicine_desc)


        submitBtn.setOnClickListener {
            val data = hashMapOf(
                "name" to medicineName.text.toString(),
                "morning" to chkm.isChecked,
                "evening" to chke.isChecked,
                "night" to chkn.isChecked,
                "quantity" to medicineDesc.text.toString()
            )

            val db = FirebaseFirestore.getInstance().collection("patients")
                .document("patient1")
                .collection("Medicines")
                .document()
                .set(data)
                .addOnSuccessListener {
                    Toast.makeText(this,"Added Successfully",Toast.LENGTH_LONG).show()
                    startActivity(Intent(this,MainMainDoctorActivity::class.java))
                }
        }
    }
}