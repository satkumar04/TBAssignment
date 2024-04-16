package com.example.techiebutlerassignment.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.techiebutlerassignment.domain.model.DataModel

@Composable
fun CardItem(data: DataModel, onItemClick: () -> Unit) {
    // Your item UI code here
    // Detect click and invoke the onItemClick lambda
    Box(
        modifier = Modifier
            .clickable { onItemClick() }
            .padding(16.dp)
    ) {
        Column {
            Text(text = "${data.id}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            data.title?.let { Text(text = it, fontSize = 14.sp, color = Color.Gray) }
        }

    }
}