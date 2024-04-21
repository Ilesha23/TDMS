package com.example.rgz.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rgz.data.db.Car
import com.example.rgz.data.repository.DataBaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: DataBaseRepository
) : ViewModel() {

    fun insert(name: String, descr: String, priceS: String) {
        val price = priceS.toDouble() // TODO: error
        val car = Car(
            name = name,
            description = descr,
            price = price
        )
        viewModelScope.launch {
            repository.insert(car)
        }
    }

    fun delete(name: String) {
        viewModelScope.launch {
            repository.delete(name)
        }
    }

}