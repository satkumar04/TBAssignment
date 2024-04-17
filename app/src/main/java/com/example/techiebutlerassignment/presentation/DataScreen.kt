package com.example.techiebutlerassignment.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.techiebutlerassignment.presentation.utils.common.ListState
import com.example.techiebutlerassignment.presentation.utils.common.LoaderState
import com.example.techiebutlerassignment.presentation.utils.route.AppScreen
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataScreen(navController: NavController) {

    val dataViewModel = DataViewModel()
    val coroutineScope = rememberCoroutineScope()
    val lazyColumnListState = rememberLazyListState()

    LaunchedEffect(Unit, block = {
        dataViewModel.getDataList()
    })
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Techie Butler Assignment") })
        }
    ) {
        if (dataViewModel.errorMessage.isEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .padding(it),
                state = lazyColumnListState

            ) {
                item(key = dataViewModel.loaderState) {
                    when (dataViewModel.loaderState) {
                        LoaderState.IDLE -> {

                        }

                        LoaderState.LOADING -> {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Spacer(modifier = Modifier.padding(20.dp))
                                CircularProgressIndicator(color = Color.Black)
                                Spacer(modifier = Modifier.padding(20.dp))
                                Text(text = "Data Loading...")
                            }

                        }

                    }
                }

                items( dataViewModel.dataList) { data ->
                    CardItem(data = data, onItemClick = {
                        val gson: Gson = GsonBuilder().create()
                        val dataJson = gson.toJson(data)


                        navController.navigate(
                            "detail/{data}" //Just modify your route accordingly
                                .replace(
                                    oldValue = "{data}",
                                    newValue = dataJson
                                )
                        ){
                            popUpTo("home_screen") {
                                inclusive = true
                            }
                        }
                    })

                }

                item(
                    key = dataViewModel.listState,
                ) {
                    when (dataViewModel.listState) {
                        ListState.LOADING -> {
                            Column(
                                modifier = Modifier
                                    .fillParentMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(8.dp),
                                    text = "Refresh Loading"
                                )

                                CircularProgressIndicator(color = Color.Black)
                            }
                        }

                        ListState.PAGINATING -> {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(text = "Pagination Loading")

                                CircularProgressIndicator(color = Color.Black)
                                LaunchedEffect(Unit, block = {
                                    delay(1.seconds)
                                    dataViewModel.loadMoreData()
                                })

                            }
                        }

                        ListState.PAGINATION_EXHAUST -> {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 6.dp, vertical = 16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {

                                TextButton(
                                    modifier = Modifier
                                        .padding(top = 8.dp),
                                    elevation = ButtonDefaults.buttonElevation(0.dp),
                                    onClick = {
                                        coroutineScope.launch {
                                            lazyColumnListState.animateScrollToItem(0)
                                        }
                                    },
                                    content = {
                                        Row(
                                            horizontalArrangement = Arrangement.Center,
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Icon(
                                                imageVector = Icons.Rounded.KeyboardArrowUp,
                                                contentDescription = ""
                                            )

                                            Text(text = "Back to Top")

                                            Icon(
                                                imageVector = Icons.Rounded.KeyboardArrowUp,
                                                contentDescription = ""
                                            )
                                        }
                                    }
                                )
                            }
                        }

                        else -> {}
                    }
                }
            }
        } else {
            Text(dataViewModel.errorMessage)
        }
    }



}