package me.keyo.cb.ui.bag

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BagViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This section is under construction!"
    }
    val text: LiveData<String> = _text
}