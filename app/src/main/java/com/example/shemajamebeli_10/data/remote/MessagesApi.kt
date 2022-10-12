package com.example.shemajamebeli_10.data.remote

import com.example.shemajamebeli_10.data.remote.model.DTOItem
import retrofit2.Response
import retrofit2.http.GET

interface MessagesApi {
    @GET("v3/80d25aee-d9a6-4e9c-b1d1-80d2a7c979bf")
    suspend fun doNetworkCall(): Response<List<DTOItem>>

}