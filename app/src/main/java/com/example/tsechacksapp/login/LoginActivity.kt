package com.example.tsechacksapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.tsechacksapp.MainActivity
import com.example.tsechacksapp.R
import com.example.tsechacksapp.doctor.MainMainDoctorActivity
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText : TextInputLayout
    private lateinit var passwordEditText: TextInputLayout
    private lateinit var loginButton : Button
    private lateinit var signuptextview : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        emailEditText = findViewById(R.id.Email_login)
        passwordEditText = findViewById(R.id.Password_login)
        loginButton = findViewById(R.id.Button_login)
        signuptextview = findViewById(R.id.textview_goto_RegisterActivity)


        loginButton.setOnClickListener{
            loginuser()
            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }
        signuptextview.setOnClickListener {
            startActivity(Intent(this,MainMainDoctorActivity::class.java))
            finish()
        }

    }

    private fun loginuser() {
        val email = emailEditText.editText?.text.toString()
        val password = passwordEditText.editText?.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty()){
            CoroutineScope(Dispatchers.IO).launch {
                try{
//                    auth.signInWithEmailAndPassword(email, password).await()
//                    withContext(Dispatchers.Main){
//                        checkloggedinstate()
//                    }

                }catch (e : Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@LoginActivity,"Logged in successfully ", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

    }

    private fun checkloggedinstate() {
//        if(auth.currentUser == null) { // not logged in
//            Toast.makeText(this, "You are not logged in ", Toast.LENGTH_SHORT).show()
//        }
//        else{
//            Toast.makeText(this, "You are  logged in ", Toast.LENGTH_SHORT).show()
//        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }

    override fun onStart() {
        super.onStart()
        checkloggedinstate()
    }
}