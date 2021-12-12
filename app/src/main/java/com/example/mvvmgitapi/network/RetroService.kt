package com.example.mvvmgitapi.network

import com.example.mvvmgitapi.RecyclerData
import com.example.mvvmgitapi.RecyclerList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("repositories")
    fun getDataFromAPI(@Query("q") query: String): Call<RecyclerList>
}