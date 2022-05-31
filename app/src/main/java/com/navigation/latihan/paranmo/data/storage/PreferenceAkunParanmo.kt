package com.navigation.latihan.paranmo.data.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.navigation.latihan.paranmo.data.LoginAccount
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferenceAkunParanmo private constructor(private val dataStoreParanmo: DataStore<Preferences>){

    fun getAkunParanmo(): Flow<LoginAccount>{
        return dataStoreParanmo.data.map {
            preferences ->
            LoginAccount(
                preferences[ACCOUNT_ID_KEY]?:"",
                preferences[ACCOUNT_NAMA_KEY]?:"",
                preferences[ACCOUNT_TOKEN_KEY]?: "",
                preferences[ACCOUNT_STATE_KEY]?: false

            )
        }
    }

    suspend fun saveAkunParanmo(loginAccount: LoginAccount){
        dataStoreParanmo.edit { preferences ->
            preferences[ACCOUNT_ID_KEY] = loginAccount.id
            preferences[ACCOUNT_NAMA_KEY] = loginAccount.name
            preferences[ACCOUNT_TOKEN_KEY] = loginAccount.token
            preferences[ACCOUNT_STATE_KEY] = loginAccount.isLogin
        }
    }

    suspend fun login(){
        dataStoreParanmo.edit { preferences ->
            preferences[ACCOUNT_STATE_KEY]= true
        }
    }


    suspend fun logoutAccountParanmo(){
        dataStoreParanmo.edit { preferences ->
            preferences[ACCOUNT_STATE_KEY] = false
        }
    }

    companion object{
        @Volatile

        private var INSTANCEPARANMOAPP : PreferenceAkunParanmo? = null

        private val ACCOUNT_ID_KEY = stringPreferencesKey("id")
        private val ACCOUNT_NAMA_KEY = stringPreferencesKey("name")
        private val ACCOUNT_TOKEN_KEY = stringPreferencesKey("token")
        private val ACCOUNT_STATE_KEY = booleanPreferencesKey("state")

        fun getInstanceParanmoApp(dataStoreParanmo: DataStore<Preferences>): PreferenceAkunParanmo{
            return INSTANCEPARANMOAPP ?: synchronized(this){
                val instanceParanmo = PreferenceAkunParanmo(dataStoreParanmo)
                INSTANCEPARANMOAPP = instanceParanmo
                instanceParanmo
            }
        }


    }

}