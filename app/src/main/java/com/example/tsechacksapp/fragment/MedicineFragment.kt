package com.example.tsechacksapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tsechacksapp.Adapter.MedicineAdapter
import com.example.tsechacksapp.R
import com.example.tsechacksapp.data.MedicineDetail
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class MedicineFragment : Fragment() {


    private lateinit var medicineRecyclerView: RecyclerView
    private lateinit var firebaseStore: FirebaseStorage
    private lateinit var storageReference: StorageReference
    private lateinit var medicineAdapter : MedicineAdapter
    private lateinit var medicineList: ArrayList<MedicineDetail>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_medicine, container, false)

        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        medicineRecyclerView = view.findViewById(R.id.patient_medicine_recyclerview_pf)

        medicineList = arrayListOf()
        medicineAdapter = MedicineAdapter(medicineList)
        medicineRecyclerView.adapter = medicineAdapter

        EventListenerAdapter()
        return view
    }

    private fun EventListenerAdapter() {
        val db = FirebaseFirestore.getInstance().collection("patients")
            .document("patient1")
            .collection("Medicines")
            .get()
            .addOnCompleteListener {
                if(it.isSuccessful){
                    for(i in it.getResult()){
                        medicineList.add(i.toObject(MedicineDetail::class.java))
                        medicineAdapter.notifyDataSetChanged()
                    }
                }
            }
        medicineAdapter.notifyDataSetChanged()
    }

}