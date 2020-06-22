package com.example.retrofits.api

import com.example.retrofits.model.Voting
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface VotingApiInterface {

    @GET("queen")//enter end point value queen
    fun getQueen(): Call<List<Voting>>//obj list

    @POST("queen")
    fun postQueen(): Call<List<Voting>>//parameters
}
