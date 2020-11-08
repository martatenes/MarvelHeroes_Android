package com.openbank.marvelheroes.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SplashViewModel: ViewModel(){

    val isLoading = ObservableBoolean()

    init {
        isLoading.set(true);
    }

    fun setIsLoading(value: Boolean){
        isLoading.set(value)
    }
}