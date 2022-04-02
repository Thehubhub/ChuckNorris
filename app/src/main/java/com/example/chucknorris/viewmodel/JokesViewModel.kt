package com.example.chucknorris.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorris.database.DbRepo
import com.example.chucknorris.rest.JokesRepository
import com.example.chucknorris.utils.JokesState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class JokesViewModel(
    private val networkRepo: JokesRepository,
    private val databaseRepo: DbRepo,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    init {
        Log.d("JokesViewModel","ViewModel initialized")
    }

    private val _sortedJokes: MutableLiveData<JokesState> = MutableLiveData(JokesState.LOADING)
    val oneSortedJoke: LiveData<JokesState> get() = _sortedJokes

    fun getSortedJokes(){
        viewModelScope.launch(ioDispatcher){
            try {
                val response = networkRepo.getAllJokes()
                if (response.isSuccessful){
                    response.body()?.let {
                        databaseRepo.insertValue(it)
                        val localData = databaseRepo.getAllJokes()
                        _sortedJokes.postValue(JokesState.SUCCESS(localData))
                    }?: throw Exception("Null Response")
                }else{
                    throw Exception("ERROR")
                }
            } catch (e: Exception){
                _sortedJokes.postValue(JokesState.ERROR(e))
                loadFromDb()
            }
        }

    }

    private suspend fun loadFromDb(){
        try{
            val localData = databaseRepo.getAllJokes()
            _sortedJokes.postValue(JokesState.SUCCESS(localData, isLocalData = true))
        }catch (e: Exception){
            _sortedJokes.postValue(JokesState.ERROR(e))
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("JokesViewModel", "ViewModel Destroyed")
    }
}