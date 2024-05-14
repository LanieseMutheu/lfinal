package net.ezra.ui.howitworks

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.ezra.R
import net.ezra.navigation.ROUTE_HOME
import net.ezra.navigation.ROUTE_HOW_IT_WORKS


@Composable
fun Howitworks(navController: NavHostController) {

    @Composable
    fun FaqItem(question: String, answer: String) {
        TODO("Not yet implemented")
    }

    LazyColumn {
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
                    .padding(10.dp)
                    .padding(top = 10.dp)

            ){

                Text(
                    modifier = Modifier
                        .clickable {
                            navController.navigate(ROUTE_HOW_IT_WORKS) {
                                popUpTo(ROUTE_HOME) { inclusive = true }
                            }
                        },
                    text = "HOW IT WORKS",
                    fontWeight = FontWeight.Medium,
                    color = Color(0xffFF5733)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "FoodshareConnect is a simple way to " +
                        "donate surplus food and help feed " +
                        "your community")

                Spacer(modifier = Modifier.height(30.dp))

                Card (
                    modifier = Modifier
                        .width(350.dp)
                        .height(700.dp)

                ){

                    Image( painter = painterResource(id = R.drawable.computer),
                        contentDescription = "computer",
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(100.dp))
                    Text(text = "Connect",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xff30058C) )

                    Text(text = "Go to the Foodshare Connect desktop or mobile site, or download the FoodshareConnect app.")

                    Spacer(modifier = Modifier.height(20.dp))

                    Image( painter = painterResource(id = R.drawable.sharepost),
                        contentDescription = "Sharepost",
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(100.dp))
                    Text(text = "Share",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xff30058C) )

                    Text(text = "Post a photo of your donation along with some basic information onto the platform,including preerred pick-up time.")

                    Spacer(modifier = Modifier.height(20.dp))

                    Image( painter = painterResource(id = R.drawable.restaurant),
                        contentDescription = "restaurant",
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(100.dp))
                    Text(text = "Match",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xff30058C))

                    Text(text = "Your post will be sent to a trusted non-profit partner in your community.")

                    Spacer(modifier = Modifier.height(20.dp))

                    Image( painter = painterResource(id = R.drawable.takeaway),
                        contentDescription = "takeaway",
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(100.dp))
                    Text(text = "Await Your Pick Up",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xff30058C) )

                    Text(text = "Your local food shelf or pantry will schedule " +
                            "a pick-up time and provide a volunteer to receive your donation.")







                }





            }



        }
    }



}

@Preview(showBackground = true)
@Composable
fun PreviewLight() {
    Howitworks(rememberNavController())
}



