package com.markvtls.whac_a_mole.model.use_cases

import com.markvtls.whac_a_mole.model.repository.GameRepository

class SaveTopScoreUseCase(
    private val repository: GameRepository
) {
    suspend operator fun invoke(topScore: Int) {
        repository.saveTopScore(topScore)
    }
}