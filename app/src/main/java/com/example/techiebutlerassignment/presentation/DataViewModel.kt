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
import com.example.techiebutlerassignment.presentation.utils.common.ListState
import com.example.techiebutlerassignment.presentation.utils.common.LoaderState


class DataViewModel : ViewModel() {
    private val useCase = GetDataUseCase()
    private var response: List<DataModel> = listOf()
    private val _dataList = mutableStateListOf<DataModel>()
    var errorMessage: String by mutableStateOf("")
    private var lastIndex = 0;


    var listState by mutableStateOf(ListState.IDLE)
    var loaderState by mutableStateOf(LoaderState.IDLE)


    val dataList: List<DataModel>
        get() = _dataList

    fun getDataList() {
        viewModelScope.launch {

            try {
                loaderState =  LoaderState.LOADING
                _dataList.clear()
                response = useCase.execute()
                if (response.size < 20) {
                    _dataList.addAll(response)

                } else {
                    listState =  ListState.PAGINATING

                    lastIndex = response.subList(0, 20).size
                    _dataList.addAll(response.subList(0, 20))
                }

            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
            loaderState =  LoaderState.IDLE
        }
    }



    fun loadMoreData() {
        if(_dataList.size < response.size) {
            listState =  ListState.PAGINATING
            lastIndex = response.subList(_dataList.size, lastIndex+_dataList.size).size
            _dataList.addAll(response.subList(_dataList.size, lastIndex+_dataList.size))
        }
        else
        {
            listState =  ListState.PAGINATION_EXHAUST
        }

    }

    override fun onCleared() {
        _dataList.clear()
        super.onCleared()
    }
}

