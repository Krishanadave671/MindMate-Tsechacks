package com.example.tsechacksapp.doctor

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.example.tsechacksapp.R
import com.example.tsechacksapp.data.ImgVidData
import com.example.tsechacksapp.data.img
import com.example.tsechacksapp.data.vid
import com.example.tsechacksapp.models.familyImageVideoModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*


class PhotoUploadFragment : Fragment() {

    private val ViewModel : familyImageVideoModel by viewModels()
    private lateinit var ImageViewTxt : TextView
    private lateinit var VideoViewTxt : TextView
    private lateinit var VideoView : VideoView
    private lateinit var ImageView: ImageView
    private lateinit var uploadbtn : Button
    private var imageUri: Uri? = null
    private var VideoUri: Uri? = null
    private var patientName: String ? = "patient1"
    private var familyName: String ? = "member1"
    private lateinit var firebaseStore: FirebaseStorage
    private lateinit var storageReference: StorageReference
    private lateinit var auth : FirebaseAuth

    val db = FirebaseFirestore.getInstance().collection("patients")
        .document(patientName!!)
        .collection("family")
        .document(familyName!!)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_photo_upload, container, false)

        ImageViewTxt = view.findViewById(R.id.image_txtvw)
        VideoViewTxt = view.findViewById(R.id.video_txtv)
        VideoView  = view.findViewById(R.id.video_view_family)
        ImageView = view.findViewById(R.id.image_view_family)
//        ImageViewTxt.setBackgroundColor(resources.getColor(R.color.primarylight))
        ImageViewTxt.background = resources.getDrawable(R.drawable.roundness)
        VideoView.background = resources.getDrawable(R.drawable.ic_add_a_video)
        uploadbtn = view.findViewById(R.id.upload)

        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        auth = Firebase.auth

        ImageViewTxt.setOnClickListener {

            VideoView.apply {
                visibility = View.INVISIBLE
            }
            ImageView.apply{
                visibility = View.VISIBLE
            }

            ImageViewTxt.background = resources.getDrawable(R.drawable.roundness)
//            ImageViewTxt.setBackgroundColor(resources.getColor(R.color.primarylight))
            VideoViewTxt.background = resources.getDrawable(R.drawable.roundness_white)
//            VideoViewTxt.setBackgroundColor(resources.getColor(R.color.white))
            ViewModel.setType("Image")
        }

        VideoViewTxt.setOnClickListener {
            VideoView.apply{
                visibility = View.VISIBLE
            }
            ImageView.apply{
                visibility = View.INVISIBLE
            }

            ImageViewTxt.background = resources.getDrawable(R.drawable.roundness_white)
//            ImageViewTxt.setBackgroundColor(resources.getColor(R.color.white))
            VideoViewTxt.background = resources.getDrawable(R.drawable.roundness)
//            VideoViewTxt.setBackgroundColor(resources.getColor(R.color.primarylight))
            ViewModel.setType("Video")
            if (VideoUri != null) {
                VideoView.start()
            }

        }

        if(ViewModel.type.value=="Video"){

        }

        uploadbtn.setOnClickListener {
            Toast.makeText(context,ViewModel.type.value.toString(), Toast.LENGTH_LONG).show()
            when(ViewModel.type.value.toString()){
                "Image" -> chooseImage()
                else -> choosevideo()
            }
        }



        return view
    }
    private fun chooseImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(intent, 0)
    }

    private fun choosevideo() {
        val intent = Intent()
        intent.type = "video/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == 0) {
            imageUri = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver,imageUri)
            val compressedBitmap = Bitmap.createScaledBitmap(bitmap,600,600,true)
            ImageView.setImageBitmap(compressedBitmap)
            uploadImage()
        }
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == 1) {
            VideoUri = data?.data
            VideoView.setVideoURI(VideoUri)
            VideoView.start()
            uploadVideo()
        }
    }

    private fun uploadVideo() {
        TODO("Upload image in firebase")
//        val vidPath = "Videos/" + patientName +
//                SimpleDateFormat("dd.MM.yyyy'|'HH.mm.ss").format(Date()).toString()
//        storageReference.putFile(VideoUri!!)
//            .addOnSuccessListener {
//                uploadDataVideo(vidPath)
//            }
//            .addOnFailureListener{
//                Toast.makeText(this,it.toString(),Toast.LENGTH_LONG).show()
//
//            }
    }

    private fun uploadImage() {
        val imgPath = "Images/" + patientName +
                SimpleDateFormat("dd.MM.yyyy'|'HH.mm.ss").format(Date()).toString()
        val ref = storageReference.child(imgPath)
        val bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver,imageUri)
        val baos = ByteArrayOutputStream()
        bitmap!!.compress(Bitmap.CompressFormat.JPEG, 20, baos)
        val bytes = baos.toByteArray()
        ref.putBytes(bytes)
            .addOnSuccessListener {
                uploadDataImage(imgPath)
            }
            .addOnFailureListener{
                Toast.makeText(context,"File Uploading Failed",Toast.LENGTH_SHORT).show()

            }


    }

    private fun uploadDataImage(imgPath: String) {
        Toast.makeText(context,"Here in upi function",Toast.LENGTH_SHORT).show()
        db.get()
            .addOnFailureListener {
                Toast.makeText(context,it.toString(),Toast.LENGTH_LONG).show()
            }
            .addOnSuccessListener {
                val data : ImgVidData? = it.toObject(ImgVidData::class.java)
                data?.Image?.add(img("chbderbcfyhuebuyfbcyebcu",imgPath))
                changeData(data)
            }
    }
    private fun uploadDataVideo(vidPath: String) {
        db.get()
            .addOnFailureListener {
                Toast.makeText(context,it.toString(),Toast.LENGTH_LONG).show()
            }
            .addOnSuccessListener {
                val data : ImgVidData? = it.toObject(ImgVidData::class.java)
                data?.Video?.add(vid("chbderbcfyhuebuyfbcyebcu",vidPath))
                changeData(data)
            }
    }


    private fun changeData(data: ImgVidData?) {
        if (data != null) {
            db.set(data).addOnSuccessListener {

            }
        }
    }



}