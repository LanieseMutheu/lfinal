package net.ezra.ui.donate

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun FundsScreen(navController: NavHostController) {
    var amount by remember { mutableStateOf("") }
    var recipient by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    )
    {
        TextField(value = amount,
            onValueChange = {amount = it},
            label = { Text(text = "Amount")},
//            KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        TextField(value = recipient,
            onValueChange = { recipient= it},
            label = { Text(text = "Recipient")},
//            KeyboardOptions(keyboardType = KeyboardType.Number)

        )


        val mContext = LocalContext.current
        OutlinedButton(
            onClick = {

                val simToolKitLaunchIntent =
                    mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                simToolKitLaunchIntent?.let { mContext.startActivity(it) }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            shape = RoundedCornerShape(5.dp),
            border = BorderStroke(2.dp, Color.Cyan)
        ) {
            Icon(imageVector = Icons.Default.Send, "", tint = Color.White)
            androidx.compose.material3.Text(text = "Send Money", color = Color.Black)


        }

    }
}


