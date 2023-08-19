package com.traven.kittyfactz.model

import com.traven.kittyfactz.Const
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repo {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor).build()

    private val api: IKittyAPI = Retrofit.Builder()
        .baseUrl(Const.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(IKittyAPI::class.java)

    suspend fun getFact() = api.getKittyFact()

}