package com.example.techiebutlerassignment.domain.repository

import com.example.techiebutlerassignment.domain.model.DataModel

interface DataRepository {
    suspend fun getData(): List<DataModel>
}