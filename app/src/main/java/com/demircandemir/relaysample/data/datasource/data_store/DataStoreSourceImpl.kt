package com.demircandemir.relaysample.data.datasource.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.demircandemir.relaysample.util.Constants.PREFERENCES_KEY
import com.demircandemir.relaysample.util.Constants.PREFERENCES_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class DataStoreSourceImpl(context: Context): DataStoreSource {

    private object PreferenceKeys {
        val surveyKey = booleanPreferencesKey(name = PREFERENCES_KEY)
    }

    private val dataStore = context.dataStore


    override suspend fun saveSurveyState(completed: Boolean) {
        dataStore.edit {  preferences ->
            preferences[PreferenceKeys.surveyKey] = completed
        }
    }

    override fun readSurveyState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if(exception is Exception){
                    emit(emptyPreferences())
                }else{
                    throw exception
                }
            }
            .map { preferences ->
                val surveyState = preferences[PreferenceKeys.surveyKey] ?: false
                surveyState }

    }
}