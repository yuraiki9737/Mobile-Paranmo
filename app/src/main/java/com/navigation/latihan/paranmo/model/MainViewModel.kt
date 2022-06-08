package com.navigation.latihan.paranmo.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.navigation.latihan.paranmo.data.response.LoginAccount
import com.navigation.latihan.paranmo.data.storage.PreferenceAkunParanmo
import kotlinx.coroutines.launch

class MainViewModel (private val preferenceAkunParanmo: PreferenceAkunParanmo) : ViewModel(){

    fun saveParanmoApp(loginAkun: LoginAccount){
        viewModelScope.launch {
            preferenceAkunParanmo.saveAkunParanmo(LoginAccount(loginAkun.id, loginAkun.name, loginAkun.token,loginAkun.isLogin  ))
        }
    }

    fun getAkunParanmo(): LiveData<LoginAccount>{
        return preferenceAkunParanmo.getAkunParanmo().asLiveData()
    }
}