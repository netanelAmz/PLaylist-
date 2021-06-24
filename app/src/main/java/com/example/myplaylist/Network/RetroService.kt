package com.example.weatherapp.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroService {

    const val BASE_URL = "https://landing.cal-online.co.il/youtube/"

//deceleration of retrofit using Gson as converterFactory
    val retrofitBuilder : Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val api :Api by lazy {
        retrofitBuilder
            .build().create(Api::class.java)
    }
}