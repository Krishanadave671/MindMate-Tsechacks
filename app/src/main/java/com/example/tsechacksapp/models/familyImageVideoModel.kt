package com.example.tsechacksapp.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class familyImageVideoModel: ViewModel() {
    private val _type =MutableLiveData<String>("Image")
    val type: LiveData<String> = _type

    private val _flag = MutableLiveData<Int>(0)
    val flag = 0

    fun setFlg(){
        _flag.value = 1;
    }

    fun setType(st: String){
        _type.value = st;
    }

}