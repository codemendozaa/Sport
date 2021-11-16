package com.example.sport.repository

import com.example.sport.data.model.TeamsList

interface SportRepository {
    suspend fun getSpanishLigaTeams(): TeamsList
}