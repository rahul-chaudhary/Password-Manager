package com.example.chaintechtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chaintechtest.NavRoutes.HOME_PAGE
import com.example.chaintechtest.ui.theme.ChainTechTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChainTechTestTheme {
            val navController =  rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = HOME_PAGE) {

                    composable(HOME_PAGE) {
                        HomePage()
                    }
                }

            }
        }
    }
}

