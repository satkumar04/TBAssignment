package com.example.techiebutlerassignment.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techiebutlerassignment.domain.model.DataModel
import com.example.techiebutlerassignment.domain.usecases.GetDataUseCase
import kotlinx.coroutines.launch

class DataViewModel : ViewModel() {
    private val useCase = GetDataUseCase()

    private val _dataList = mutableStateListOf<DataModel>()
    var errorMessage: String by mutableStateOf("")

    val dataList: List<DataModel>
        get() = _dataList

    fun getDataList() {
        viewModelScope.launch {

            try {
                _dataList.addAll(useCase.execute())

            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}