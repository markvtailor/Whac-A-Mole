package com.markvtls.whac_a_mole.model.repository

import kotlinx.coroutines.flow.Flow

interface GameRepository {

    suspend fun saveTopScore(topScore: Int)

    fun getTopScore(): Flow<Int>

}