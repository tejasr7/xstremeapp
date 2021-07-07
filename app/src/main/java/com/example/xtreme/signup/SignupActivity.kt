package com.example.xtreme.signup

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.xtreme.R
import com.example.xtreme.api.RetrofitClient
import kotlinx.android.synthetic.main.activity_signup.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        btn_signup.setOnClickListener {
            val username = edtxt_username.text.toString().trim()
            val email = edtxt_email.text.toString()
            val password = edtxt_password.text.toString()

            if (TextUtils.isEmpty(username)){
                edtxt_username.error = "Empty credentials"
                edtxt_username.requestFocus()
                return@setOnClickListener
            }
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
//            else {
//                registerUser(email, password)
//            }

            signup_progressbar.visibility = View.VISIBLE
            RetrofitClient.instance.registerUser(username, email, password)
                .enqueue(object:Callback<SignupResult>{
                    override fun onFailure(call: Call<SignupResult>, t: Throwable) {
                        signup_progressbar.visibility = View.INVISIBLE
                        Toast.makeText(this@SignupActivity, t.message, Toast.LENGTH_LONG).show()
                    }
                    override fun onResponse(
                        call: Call<SignupResult>,
                        response: Response<SignupResult>) {
                        if(!response.body()?.error!!) {
                            signup_progressbar.visibility = View.INVISIBLE
                            Toast.makeText(this@SignupActivity, response.body()?.message, Toast.LENGTH_LONG).show()
                            finish()
                        } else {
                            signup_progressbar.visibility = View.INVISIBLE
                            Toast.makeText(this@SignupActivity, response.body()?.message, Toast.LENGTH_LONG).show()
                        }
//                            signup_progressbar.visibility = View.INVISIBLE
//                        Toast.makeText(this@SignupActivity, response.body()?.message, Toast.LENGTH_LONG).show()
                    }
                })
//            val call: Call<ResponseBody>? = RetrofitClient()
//                .getInstance()
//                ?.getApi()
//                ?.registerUser(username, email, password)

//            call?.enqueue(object:Callback<ResponseBody> {
//                override fun onResponse(call:Call<ResponseBody>, response: Response<ResponseBody>) {
//                    try {
//                        val s = response.body()?.string()
//                        Toast.makeText(this@SignupActivity, s, Toast.LENGTH_LONG).show()
//                    } catch (e: IOException) {
//                        e.printStackTrace()
//                    }
////                    if (response.code() === 201) {
////                        val dr = response.body()
////                        Toast.makeText(this@SignupActivity, dr.getMsg(), Toast.LENGTH_LONG).show()
////                    }
////                    else if (response.code() === 422) {
////                        Toast.makeText(this@SignupActivity, "User already exist", Toast.LENGTH_LONG).show()
////                    }
//                }
//                override fun onFailure(call:Call<ResponseBody>, t:Throwable) {
//                    Toast.makeText(this@SignupActivity, t.message, Toast.LENGTH_LONG).show()
//                }
//            })
        }




    }

//    private fun registerUser(email: String, password: String) {
//
//    }
}
