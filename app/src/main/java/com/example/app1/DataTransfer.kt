package com.example.app1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataTransfer:ViewModel() {

    val message:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }


    val messagefragment2:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}