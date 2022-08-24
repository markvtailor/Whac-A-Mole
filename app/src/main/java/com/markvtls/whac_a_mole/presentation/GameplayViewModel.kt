package com.markvtls.whac_a_mole.presentation

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.markvtls.whac_a_mole.data.repository.GameRepositoryImpl
import com.markvtls.whac_a_mole.data.source.DataStore
import com.markvtls.whac_a_mole.model.use_cases.GetTopScoreUseCase
import com.markvtls.whac_a_mole.model.use_cases.SaveTopScoreUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameplayViewModel(application: Application) : ViewModel() {

    private val dataStore = DataStore(application.applicationContext)
    private val gameRepository = GameRepositoryImpl(dataStore)
    private val saveTopScore = SaveTopScoreUseCase(gameRepository)
    private val getTopScore = GetTopScoreUseCase(gameRepository)
    private var _topScore = MutableLiveData<Int>()
    val topScore get() = _topScore

    init {
        getLastTopScore()
    }

    private fun getLastTopScore() {
        viewModelScope.launch(Dispatchers.IO) {
            getTopScore().collect { lastTopScore ->
                _topScore.postValue(lastTopScore)
            }
        }
    }

    fun saveNewTopScore(newTopScore: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getTopScore().collect {
                if (newTopScore > it) {
                    saveTopScore(newTopScore)
                }
            }

        }
    }
}

class GameplayViewModelFactory(private val application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameplayViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameplayViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}