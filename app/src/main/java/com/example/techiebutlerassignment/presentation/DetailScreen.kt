package com.example.techiebutlerassignment.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.techiebutlerassignment.domain.model.DataModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(jasonData: String) {
    val navController = rememberNavController()
    val gson: Gson = GsonBuilder().create()
    val data: DataModel = gson.fromJson(jasonData, DataModel::class.java)


    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Id: ${data.id}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "User Id: ${data.userId}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            data.title?.let {
                Text(text = "Title: $it ", fontSize = 14.sp, color = Color.Gray)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Description: ${data.body}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

    }


}