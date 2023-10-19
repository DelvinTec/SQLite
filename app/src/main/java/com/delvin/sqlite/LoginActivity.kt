package com.delvin.sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.delvin.sqlite.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

   private lateinit var binding: ActivityLoginBinding
   private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper= DatabaseHelper(this)

        binding.LoginButton.setOnClickListener{
            val loginUsername=binding.LoginUsername.text.toString()
            val loginPassword=binding.LoginPassword.text.toString()
            loginDatabase(loginUsername,loginPassword)
        }
        binding.signupRedirect.setOnClickListener{
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loginDatabase(username: String, password: String){
        val userExists = databaseHelper.readUser(username,password)
        if (userExists){
            Toast.makeText(this,"Login exitoso", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this, "Login error", Toast.LENGTH_SHORT).show()
        }
    }
}