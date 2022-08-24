package com.markvtls.whac_a_mole.model.use_cases

import com.markvtls.whac_a_mole.model.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTopScoreUseCase(
    private val repository: GameRepository
) {
    operator fun invoke(): Flow<Int> = flow {
        repository.getTopScore().collect { topScore ->
            emit(topScore)
        }
    }
}