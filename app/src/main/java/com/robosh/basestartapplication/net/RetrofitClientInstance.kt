package com.robosh.basestartapplication.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {

    private lateinit var retrofit: Retrofit
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    val retrofitInstance: Retrofit
        get() {
            if (this::retrofit.isInitialized.not()) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}
