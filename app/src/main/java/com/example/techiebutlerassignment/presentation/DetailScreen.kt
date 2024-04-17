package com.example.techiebutlerassignment.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.techiebutlerassignment.domain.model.DataModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(jasonData: String) {
    val gson: Gson = GsonBuilder().create()
    val data: DataModel = gson.fromJson(jasonData, DataModel::class.java)


    Box(
        modifier = Modifier
            .padding(0.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Detailed view", fontSize = 20.sp,
                fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))
            DetailProperty(label = "Id", value ="${data.id}" )
            DetailProperty(label = "UserId", value ="${data.userId}" )
            DetailProperty(label = "Title", value ="${data.title}" )
            DetailProperty(label = "Description", value ="${data.body}" )

        }

    }
}

@Composable
fun DetailProperty(label: String, value: String) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Text(
            text = label,
            modifier = Modifier.height(24.dp),
            color = Color.Gray,
            style = MaterialTheme.typography.titleSmall

        )
        Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        Divider(modifier = Modifier.padding(bottom = 4.dp))
    }
}
