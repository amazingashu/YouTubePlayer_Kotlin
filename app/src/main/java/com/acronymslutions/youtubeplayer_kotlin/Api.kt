package com.acronymslutions.youtubeplayer_kotlin

import retrofit2.Call
import retrofit2.http.GET


interface Api {
    @get:GET("marvel")
    val heroes:Call<List<Any?>?>


    companion object {
        const val BASE_URL = "https://simplifiedcoding.net/demos/"
    }
}