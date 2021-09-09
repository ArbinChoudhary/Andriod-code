package com.hotelroommanagementsystem.api


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceBuilder {
//      private const val BASE_URL= "http://10.0.2.2:3000/"
//    private const val BASE_URL= "http://localhost:3000/"
        private const val BASE_URL= "http://192.168.18.31:3000/"


    var token: String? = null
    var userid: String? = null

    private val okhttp = OkHttpClient.Builder()
    private val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttp.build())

    //Create retrofit instance
    private val retrofit = retrofitBuilder.build()

    //Generic function
    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }


    // Load image path in Service Builder class
    fun loadImagePath(): String {
        val arr = BASE_URL.split("/").toTypedArray()
        return arr[0] + "/" + arr[1] + arr[2] + "/"
    }

    fun loadImagePaths(): String {
//        val arr = BASE_URL.split("/").toTypedArray()
//        return arr[0] + "/" + arr[1] + arr[2] + "/"
        return BASE_URL + "/"
    }
}