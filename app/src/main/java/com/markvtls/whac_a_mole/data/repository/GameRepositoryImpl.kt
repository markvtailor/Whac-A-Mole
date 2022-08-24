package com.markvtls.whac_a_mole.data.repository

import com.markvtls.whac_a_mole.data.source.DataStore
import com.markvtls.whac_a_mole.model.repository.GameRepository
import kotlinx.coroutines.flow.Flow

class GameRepositoryImpl(
    private val dataStore: DataStore
): GameRepository {
    override suspend fun saveTopScore(topScore: Int) {
        dataStore.saveTopScore(topScore)
    }

    override fun getTopScore(): Flow<Int> {
        return dataStore.scoreFlow
    }
}