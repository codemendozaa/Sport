package com.example.sport.repository


import com.example.sport.data.model.TeamsList
import com.example.sport.data.remote.TeamsDataSource

class SportRepositoryImpl(private val dataSource: TeamsDataSource) : SportRepository {

    override suspend fun getSpanishLigaTeams(): TeamsList = dataSource.getSpanishLigaTeams()
}