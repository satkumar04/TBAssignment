package com.example.techiebutlerassignment.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.gson.Gson
import com.google.gson.GsonBuilder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataScreen(navController: NavController) {

    val vm = DataViewModel()

    LaunchedEffect(Unit, block = {
        vm.getDataList()
    })
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Techie Butler Assignment") })
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)

        ) {
            items(vm.dataList) { data ->

                CardItem(data = data, onItemClick ={
                    val gson: Gson = GsonBuilder().create()
                    val dataJson = gson.toJson(data)

                    navController.navigate(
                        "detail/{data}" //Just modify your route accordingly
                            .replace(
                                oldValue = "{data}",
                                newValue = dataJson
                            )
                    )
                })

                }
            }
        }



}