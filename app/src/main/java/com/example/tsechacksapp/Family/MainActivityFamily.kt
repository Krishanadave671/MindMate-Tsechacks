package com.example.tsechacksapp.Family

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.tsechacksapp.R
import com.example.tsechacksapp.models.familyImageVideoModel
import java.net.URI
import kotlin.math.absoluteValue


class MainActivityFamily : AppCompatActivity() {

    private val ViewModel : familyImageVideoModel by viewModels()
    private lateinit var ImageViewTxt : TextView
    private lateinit var VideoViewTxt : TextView
    private lateinit var VideoView : VideoView
    private lateinit var ImageView: ImageView
    private lateinit var Reload: ImageView
    private lateinit var uploadbtn : Button
    private var imageUri: Uri? = null
    private var VideoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_family)
        ImageViewTxt = findViewById(R.id.image_txtvw)
        VideoViewTxt = findViewById(R.id.video_txtv)
        VideoView  = findViewById(R.id.video_view_family)
        ImageView = findViewById(R.id.image_view_family)
        ImageViewTxt.setBackgroundColor(resources.getColor(R.color.primary))
        uploadbtn = findViewById(R.id.upload)
        Reload = findViewById(R.id.reload)

        ImageViewTxt.setOnClickListener {

            VideoView.apply {
                visibility = View.INVISIBLE
            }
            ImageView.apply{
                visibility = View.VISIBLE
            }
            Reload.visibility = View.INVISIBLE
            ImageViewTxt.setBackgroundColor(resources.getColor(R.color.primary))
            VideoViewTxt.setBackgroundColor(resources.getColor(R.color.white))
            ViewModel.setType("Image")
        }

        VideoViewTxt.setOnClickListener {
            VideoView.apply{
                visibility = View.VISIBLE
            }
            ImageView.apply{
                visibility = View.INVISIBLE
            }
            Reload.visibility = View.VISIBLE
            ImageViewTxt.setBackgroundColor(resources.getColor(R.color.white))
            VideoViewTxt.setBackgroundColor(resources.getColor(R.color.primary))
            ViewModel.setType("Video")
            if (VideoUri != null) {
                VideoView.start()
            }

        }

        if(ViewModel.type.value=="Video"){
            Reload.setOnClickListener {
                VideoView.start()
            }
        }

        uploadbtn.setOnClickListener {
            Toast.makeText(this,ViewModel.type.value.toString(),Toast.LENGTH_LONG).show()
            when(ViewModel.type.value.toString()){
                "Image" -> chooseImage()
                 else -> choosevideo()
            }
        }

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

    fun ToastMessage(st: String){
        Log.e("Video/Image",st)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 0) {
            imageUri = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,imageUri)
            val compressedBitmap = Bitmap.createScaledBitmap(bitmap,600,600,true)
            ImageView.setImageBitmap(compressedBitmap)
            ToastMessage(imageUri.toString())
        }
        if (resultCode == RESULT_OK && requestCode == 1) {
            VideoUri = data?.data
            VideoView.setVideoURI(VideoUri)
            VideoView.start()
            ToastMessage(VideoUri.toString())
        }
    }


}