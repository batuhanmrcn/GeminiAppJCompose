package com.example.appjc.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.appjc.ui.theme.AppJCTheme
import com.example.appjc.viewmodel.ChatViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val chatViewModel = ViewModelProvider(this)[ChatViewModel::class.java]

        setContent {
            AppJCTheme {

                var isLoggedIn by remember { mutableStateOf(false) }

                if (isLoggedIn) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        ChatPage(modifier = Modifier.padding(innerPadding), viewModel = chatViewModel)
                    }
                } else {
                    LoginScreen(onLogin = { email, password ->
                        // Giriş işlemini burada gerçekleştir
                        // Giriş başarılıysa, isLoggedIn değerini true olarak güncelle
                        isLoggedIn = true
                    })
                }
            }
        }
    }
}