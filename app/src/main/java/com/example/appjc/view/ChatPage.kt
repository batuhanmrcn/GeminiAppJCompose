package com.example.appjc.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appjc.R
import com.example.appjc.model.MessageModel
import com.example.appjc.viewmodel.ChatViewModel



@Composable
fun ChatPage(modifier: Modifier = Modifier, viewModel: ChatViewModel) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.wp),
            contentDescription = null,
            contentScale = ContentScale.Crop, // Scale image to fill the entire box
            modifier = Modifier.fillMaxSize()
        )

        // Dark overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                MessageList(
                    modifier = Modifier.weight(1f),
                    messageList = viewModel.messageList
                )
                MessageInput(
                    onMessageSend = {
                        viewModel.sendMessage(it)
                    }
                )
            }
        }
    }
}

@Composable
fun MessageList(modifier: Modifier = Modifier, messageList: List<MessageModel>) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        reverseLayout = false
    ) {
        items(messageList) { message ->
            MessageItem(message = message)
        }
    }
}

@Composable
fun MessageItem(message: MessageModel) {
    val backgroundColor = if (message.role == "user") Color(0xFF00796B) else Color(0xFF004D40)
    val horizontalAlignment = if (message.role == "user") Alignment.Start else Alignment.End
    val title = when (message.role) {
        "user" -> "SEN"
        "model" -> "Gemini"
        else -> ""
    }

    Column(
        horizontalAlignment = horizontalAlignment,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        if (title.isNotEmpty()) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 8.dp, bottom = 2.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp)) // Mesaj ile başlık arasında boşluk
        Box(
            modifier = Modifier
                .background(backgroundColor, shape = RoundedCornerShape(16.dp))
                .padding(12.dp)
                .wrapContentSize() // İçeriği saran boyut
        ) {
            Text(
                text = message.message,
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageInput(onMessageSend: (String) -> Unit) {
    var message by remember {
        mutableStateOf("")
    }

    Row(
        modifier = Modifier
            .padding(8.dp)
            .background(Color(0xFF444444), RoundedCornerShape(24.dp)) // Darker background and rounded corners
            .clip(RoundedCornerShape(24.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .background(Color.Transparent) // Transparent background for text field
                .padding(horizontal = 8.dp), // Add padding inside text field
            value = message,
            onValueChange = {
                message = it
            },
            placeholder = {
                Text("Type a message...", color = Color(0xFFCCCCCC)) // Softer placeholder color
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent, // Transparent border when focused
                unfocusedBorderColor = Color.Transparent, // Transparent border when not focused
                cursorColor = Color(0xFF1E88E5), // Cursor color
                containerColor = Color.Transparent, // No background color for the text field itself

            ),
            textStyle = TextStyle(color = Color.White, fontSize = 16.sp), // Set text color and size
            shape = RoundedCornerShape(0.dp), // No shape applied to remove the border
            maxLines = 1,
            singleLine = true
        )
        IconButton(
            onClick = {
                if (message.isNotBlank()) {
                    onMessageSend(message)
                    message = ""
                }
            },
            modifier = Modifier
                .background(Color(0xFF1E88E5), RoundedCornerShape(50.dp)) // Circular icon button
                .clip(RoundedCornerShape(50.dp)) // Fully rounded corners
                .padding(10.dp) // Padding for the icon button
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Send",
                tint = Color.White, // Icon color
                modifier = Modifier.size(24.dp) // Icon size
            )
        }
    }
}
