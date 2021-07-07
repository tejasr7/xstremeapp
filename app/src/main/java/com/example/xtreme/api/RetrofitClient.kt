package com.example.xtreme.api

import android.util.Base64
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private  val BASE_URL = "http://ec2-13-126-206-138.ap-south-1.compute.amazonaws.com/"
    private val AUTH = "Basic " + Base64.encodeToString(("admin:Tejasranveer7").toByteArray(), Base64.NO_WRAP)

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("Authorization", AUTH)
                .method(original.method(), original.body())

            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()

    var gson = GsonBuilder()
        .setLenient()
        .create()

    val instance: ApiInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

        retrofit.create(ApiInterface::class.java)
    }
}

//class RetrofitClient(){
//
//    private val BASE_URL = "http://ec2-13-126-206-138.ap-south-1.compute.amazonaws.com/v1"
//
//    private var mInstance: RetrofitClient? = null
//    private var retrofit: Retrofit? = null
//
////    init {
////        retrofit = Retrofit.Builder()
////            .baseUrl(BASE_URL)
////            .addConverterFactory(GsonConverterFactory.create())
////            //.client(okHttpClient)
////            .build()
////    }
//      private fun RetrofitClient(): RetrofitClient? {
////        val okHttpClient = OkHttpClient.Builder()
////            .addInterceptor(
////                object : Interceptor() {
////                    @Throws(IOException::class)
////                    override fun intercept(chain: Interceptor.Chain): Response? {
////                        val original: Request = chain.request()
////                        val requestBuilder: Request.Builder = original.newBuilder()
////                            .addHeader("Authorization", AUTH)
////                            .method(original.method(), original.body())
////                        val request: Request = requestBuilder.build()
////                        return chain.proceed(request)
////                    }
////                }
////            ).build()
//        retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            //.client(okHttpClient)
//            .build()
//    return RetrofitClient()
//    }
//
//    @Synchronized
//    fun getInstance(): RetrofitClient? {
//        if (mInstance == null) {
//            mInstance = RetrofitClient()
//        }
//        return mInstance
//    }
//
//    fun getApi(): ApiInterface? {
//        return retrofit?.create(ApiInterface::class.java)
//    }
//
//}

