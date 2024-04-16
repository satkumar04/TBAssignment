package com.example.techiebutlerassignment.core.network


import com.example.techiebutlerassignment.domain.model.DataModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET



const val BASE_URL = "https://jsonplaceholder.typicode.com/"


interface DataApiService {
    @GET("posts")
    suspend fun getData(): List<DataModel>

    companion object {
        private var apiService: DataApiService? = null
        fun getInstance(): DataApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(DataApiService::class.java)
            }
            return apiService!!
        }
    }
}