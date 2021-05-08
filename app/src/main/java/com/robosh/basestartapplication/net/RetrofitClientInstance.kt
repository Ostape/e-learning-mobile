package com.robosh.basestartapplication.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {

    var YOUR_TOKEN: String = ""

    private lateinit var retrofit: Retrofit
    private const val BASE_URL =
        "http://diplomabort-env.eba-ex2z2wxi.us-east-2.elasticbeanstalk.com/"

    val retrofitInstance: Retrofit
        get() {
            if (this::retrofit.isInitialized.not()) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);

                val httpClient = OkHttpClient.Builder()
                httpClient.addInterceptor(logging)

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(OkHttpClient.Builder().addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer_${YOUR_TOKEN}").build()
                        chain.proceed(request)
                    }.build())
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}
