package com.example.sport.repository

import com.example.sport.aplication.AppConstants
import com.example.sport.data.model.TeamsList
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("lookup_all_teams.php")
    suspend fun getSpanishLigaTeams(@Query("id")id: String): TeamsList

}

object RetrofitClient {
    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}