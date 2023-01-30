package com.alfredomaldonado.animalesapi.server

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RemoteConnection {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val okHttpClient= OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    private val builder = Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/breed/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: ApiService = builder.create()
}