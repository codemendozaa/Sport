package com.example.sport.data.remote

import com.example.sport.aplication.AppConstants
import com.example.sport.data.model.TeamsList
import com.example.sport.repository.WebService

class TeamsDataSource(private val webService: WebService) {

    suspend fun getSpanishLigaTeams(): TeamsList = webService.getSpanishLigaTeams(AppConstants.API_ID.toString(),)
}