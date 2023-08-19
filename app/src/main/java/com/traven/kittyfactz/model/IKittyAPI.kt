package com.traven.kittyfactz.model

import retrofit2.http.GET

interface IKittyAPI {

    @GET("fact")
    suspend fun getKittyFact(): FactDTO

}