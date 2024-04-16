package com.example.techiebutlerassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.techiebutlerassignment.presentation.utils.route.NavGraph
import com.example.techiebutlerassignment.ui.theme.TechieButlerAssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechieButlerAssignmentTheme {
                NavGraph()
            }
        }
    }
}



