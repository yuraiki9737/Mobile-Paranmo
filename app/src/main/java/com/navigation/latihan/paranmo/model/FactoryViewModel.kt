package com.navigation.latihan.paranmo.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.navigation.latihan.paranmo.data.storage.PreferenceAkunParanmo
import java.lang.IllegalArgumentException

class FactoryViewModel (private val preferenceAkunParanmo: PreferenceAkunParanmo): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(preferenceAkunParanmo)as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(preferenceAkunParanmo) as T
            }

                else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}