package com.example.xtreme.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.xtreme.api.LoginResponse
import com.example.xtreme.api.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserLiveData {

    fun userLogin(email: String, password: String): LiveData<String> {

        val loginResponse = MutableLiveData<String>()

        RetrofitClient.instance.userLogin(email, password)               // this is bad practive don't use this,,, convert this to dependency injection
            .enqueue(object: Callback<LoginResponse> { // LoginResponse
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    //Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_LONG).show()
                    loginResponse.value = t.message
                }
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>) {

                    if(!response.isSuccessful) {
                        loginResponse.value = response.body()?.toString()
                    } else {
                        loginResponse.value = response.errorBody().toString()
                    }
                }

            })
        return loginResponse
    }
}