package com.navigation.latihan.paranmo.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.navigation.latihan.paranmo.data.LoginAccount
import com.navigation.latihan.paranmo.data.storage.PreferenceAkunParanmo
import kotlinx.coroutines.launch

class HomeViewModel (private val preferenceAkunParanmo: PreferenceAkunParanmo): ViewModel() {

    fun getUserParanmo(): LiveData<LoginAccount>{
        return preferenceAkunParanmo.getAkunParanmo().asLiveData()
    }

    fun logout(){
        viewModelScope.launch {
            preferenceAkunParanmo.logoutAccountParanmo()
        }
    }
}