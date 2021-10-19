package com.hackbyte.newsapplication.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.hackbyte.newsapplication.ui.MainViewModel
import com.hackbyte.newsapplication.ui.theme.NewsApplicationTheme
import com.hackbyte.newsapplication.ui.views.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setContent {
            NewsApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Navigation(
                        navController = rememberNavController(),
                        mainViewModel = mainViewModel
                    )
                }
            }
        }
    }

}

