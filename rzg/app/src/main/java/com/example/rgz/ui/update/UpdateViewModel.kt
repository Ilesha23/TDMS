package com.example.rgz.ui.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rgz.data.repository.DataBaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(
    private val repository: DataBaseRepository
) : ViewModel() {

    val carInfo = MutableStateFlow<List<String>>(emptyList())

    init {
        viewModelScope.launch {
            val result = repository.getLastUpdated()
            if (result.isSuccess) {
                val carDb = result.getOrNull()
                carDb?.let { car ->
                    val carInfoList = listOf(car.name, car.description, car.price.toString())
                    carInfo.value = carInfoList
                }
            }
        }
    }

}