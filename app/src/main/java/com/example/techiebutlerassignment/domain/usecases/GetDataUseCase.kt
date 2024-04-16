package com.example.techiebutlerassignment.domain.usecases

import com.example.techiebutlerassignment.data.DataRepositoryImpl
import com.example.techiebutlerassignment.domain.model.DataModel

class GetDataUseCase {
    private val repository = DataRepositoryImpl()
     suspend fun execute(): List<DataModel> {
        return repository.getData()
    }
}