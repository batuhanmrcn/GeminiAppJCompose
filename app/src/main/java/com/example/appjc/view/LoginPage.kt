package com.example.appjc.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onLogin: (String, String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF000000),
                        Color(0xFF0A0A0A)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome Back",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username", color = Color.White) },
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color.White,
                    containerColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,  // Çerçeve rengini şeffaf yap
                    unfocusedBorderColor = Color.Transparent // Çerçeve rengini şeffaf yap
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF424242).copy(alpha = 0.3f))
                    .padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color.White) },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color.White,
                    containerColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,  // Çerçeve rengini şeffaf yap
                    unfocusedBorderColor = Color.Transparent // Çerçeve rengini şeffaf yap
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF424242).copy(alpha = 0.3f))
                    .padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onLogin(username, password) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5)),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF1E88E5),
                                Color(0xFF42A5F5)
                            )
                        )
                    )
                    .clip(RoundedCornerShape(24.dp))
            ) {
                Text(text = "Log In", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}
