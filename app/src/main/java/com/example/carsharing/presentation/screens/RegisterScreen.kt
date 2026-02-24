package com.example.carsharing.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carsharing.R
import com.example.carsharing.domain.model.UserRole
import com.example.carsharing.ui.theme.Blue10
import com.example.carsharing.ui.theme.Blue20
import com.example.carsharing.ui.theme.Grey10

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onRegisterClick: (fullName: String, email: String, password: String) -> Unit,
    onLoginClick: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 80.dp),

        ){

        Text(
            text = "Nice to know you!",
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "it's your first time to use Carsharing",
            color = Grey10,
            fontSize = 14.sp
        )

        Spacer(Modifier.height(28.dp))
        Text(
            text = "Full name",
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
        Spacer(Modifier.height(4.dp))
        CustomTextField(
            label = "Your full name",
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )

        Spacer(Modifier.height(28.dp))
        Text(
            text = "Email address",
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
        Spacer(Modifier.height(4.dp))
        CustomTextField(
            label = "Your email address",
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )

        Spacer(Modifier.height(28.dp))
        Text(
            text = "Password",
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
        Spacer(Modifier.height(4.dp))
        CustomTextField(
            label = "Your password",
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            isPasswordField = true
        )

        Spacer(Modifier.height(32.dp))
        AuthButton(
            onClickListener = {
                onRegisterClick(name, email, password)
            },
            text = "Register"
        )
        Spacer(Modifier.height(28.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .width(10.dp)
                    .weight(0.5f),
                thickness = 2.dp,
                color = Color.LightGray
            )
            Text(
                text = "or register with",
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 16.dp),
                fontSize = 14.sp
            )
            HorizontalDivider(
                modifier = Modifier
                    .width(10.dp)
                    .weight(0.5f),
                thickness = 2.dp,
                color = Color.LightGray
            )
        }
        Spacer(Modifier.height(16.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            CustomIconButton(R.drawable.ic_google)
            CustomIconButton(R.drawable.ic_facebook)
        }
        Spacer(Modifier.height(20.dp))
        Row (
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "Already have a Carsharing account? ",
                color = Grey10
            )
            Text(
                text = "Login",
                fontWeight = FontWeight.Medium,
                color = Blue10,
                modifier = Modifier.clickable { onLoginClick() }
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    RegisterScreen(
        onRegisterClick = { _, _, _ -> },
        onLoginClick = {}
    )
}



