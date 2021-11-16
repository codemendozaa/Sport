package com.example.sport.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.sport.core.Resource
import com.example.sport.repository.SportRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class SportViewModel (private val repo:SportRepository): ViewModel() {

    fun fetchScreenTeams() = liveData(Dispatchers.IO){
        emit(Resource.Loading())

        try {
            emit(Resource.Success(repo.getSpanishLigaTeams()))
        }catch (e:Exception){
            emit((Resource.Failure(e)))
        }
    }


}

class SportViewModelFactory(private val repo:SportRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(SportRepository::class.java).newInstance(repo)
    }
}