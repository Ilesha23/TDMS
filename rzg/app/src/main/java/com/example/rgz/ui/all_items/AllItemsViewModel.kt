package com.example.rgz.ui.all_items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rgz.data.db.Car
import com.example.rgz.data.repository.DataBaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllItemsViewModel @Inject constructor(
    private val repository: DataBaseRepository
) : ViewModel() {

    val items = MutableStateFlow<List<Car>>(emptyList())

    init {
        viewModelScope.launch {
            val result = repository.getAll()
            if (result.isSuccess) {
                items.value = result.getOrNull() ?: emptyList()
            }
        }
    }

}