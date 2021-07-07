package com.example.xtreme.api

import com.example.xtreme.signup.SignupResult
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("v1/registerUser.php")
    fun registerUser(
        @Field("username") username: String?,
        @Field("email") email: String?,
        @Field("password") password: String?
    ): Call<SignupResult>

    @FormUrlEncoded
    @POST("v1/login.php")
    fun userLogin(
        @Field("email") email: String?,
        @Field("password") password: String?
    ): Call<LoginResponse>  // LoginResponse

}