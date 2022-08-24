package com.markvtls.whac_a_mole.data.source

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


private const val USER_DATA = "USER_DATA"
private val SCORE = intPreferencesKey("top_score")

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = USER_DATA
)
class DataStore(context: Context) {

    private val dataStore = context.dataStore

    suspend fun saveTopScore(score: Int) {
        dataStore.edit { preferences ->
            preferences[SCORE] = score
        }
    }

    val scoreFlow: Flow<Int> = dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[SCORE] ?: 0
        }

}