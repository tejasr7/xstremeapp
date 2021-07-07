package com.example.xtreme.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.example.xtreme.MainActivity
import com.example.xtreme.R
import com.example.xtreme.api.LoginResponse
import com.example.xtreme.api.RetrofitClient
import com.example.xtreme.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            val email = edtxt_email.text.toString()
            val password = edtxt_password.text.toString()

            if (TextUtils.isEmpty(email)){   // || TextUtils.isEmpty(password)
                edtxt_email.error = "Empty credentials"
                edtxt_email.requestFocus()
                return@setOnClickListener
            } else if (TextUtils.isEmpty(password)){
                edtxt_password.error = "Empty field"
                edtxt_password.requestFocus()
                return@setOnClickListener
            } else if (password.length < 6) {
                edtxt_password.error = "Password is too short"
                edtxt_password.requestFocus()
            }

            loading.visibility = View.VISIBLE
//            RetrofitClient.instance.userLogin(email, password)
//                .enqueue(object: Callback<LoginResponse>{
//                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                        loading.visibility = View.INVISIBLE
//                        Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_LONG).show()
//                    }
//                    override fun onResponse(
//                        call: Call<LoginResponse>,
//                        response: Response<LoginResponse>) {
//
//                        if(!response.body()?.error!!) {
//                            loading.visibility = View.INVISIBLE
//                            SharedPrefManager.getInstance(applicationContext).saveUser(response.body()!!.user)
//                            val intent = Intent(applicationContext, MainActivity::class.java)
//                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                            startActivity(intent)
//                            Toast.makeText(this@LoginActivity, response.body()?.message, Toast.LENGTH_LONG).show()
//                        } else {
//                            loading.visibility = View.INVISIBLE
//                            Toast.makeText(this@LoginActivity, response.body()?.message, Toast.LENGTH_LONG).show()
//                        }
//                    }
//
//                })
        }
    }

    override fun onStart() {
        super.onStart()
//        if (SharedPrefManager.getInstance(this).isLoggedIn){
//            val intent = Intent(applicationContext, MainActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(intent)
//        }
    }

}
