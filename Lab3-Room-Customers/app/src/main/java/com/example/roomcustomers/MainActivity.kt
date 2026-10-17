package com.example.roomcustomers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roomcustomers.data.AppDatabase
import com.example.roomcustomers.ui.CustomerApp
import com.example.roomcustomers.ui.CustomerViewModel
import com.example.roomcustomers.ui.CustomerViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.getDatabase(applicationContext)

        setContent {
            MaterialTheme {
                val viewModel: CustomerViewModel = viewModel(
                    factory = CustomerViewModelFactory(database.customerDao())
                )
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CustomerApp(viewModel, Modifier.padding(innerPadding))
                }
            }
        }
    }
}
