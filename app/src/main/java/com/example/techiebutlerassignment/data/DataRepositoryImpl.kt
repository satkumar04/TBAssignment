package com.example.techiebutlerassignment.data

import com.example.techiebutlerassignment.core.network.DataApiService
import com.example.techiebutlerassignment.domain.model.DataModel
import com.example.techiebutlerassignment.domain.repository.DataRepository

class DataRepositoryImpl:DataRepository {
    private val apiService = DataApiService.getInstance()
    override suspend fun getData(): List<DataModel> {
      return apiService.getData()
    }
}