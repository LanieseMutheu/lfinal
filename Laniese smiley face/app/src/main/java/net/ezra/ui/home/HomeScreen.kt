package net.ezra.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.ezra.navigation.ROUTE_HOME
import net.ezra.R
import androidx.lifecycle.ViewModel
import net.ezra.navigation.ROUTE_DONATE_FOOD
import net.ezra.navigation.ROUTE_HOW_IT_WORKS
import net.ezra.navigation.ROUTE_LOGIN
import net.ezra.navigation.ROUTE_VIEW_DONATIONS

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {BottomBar()}





//        topBar = {
//            CenterAlignedTopAppBar(
//                title = {
//                    Text(text = "Donations")
//                },
//                navigationIcon = {
//                    IconButton(onClick = {
//                        navController.navigate(ROUTE_HOME) {
//                            popUpTo(ROUTE_VIEW_DONATIONS) { inclusive = true }
//                        }
//                    }) {
//                        Icon(
//                            Icons.AutoMirrored.Filled.ArrowBack,
//                            "backIcon",
//                            tint = Color.White
//                        )
//                    }
//                },
//
//
//                actions = {
//                    IconButton(onClick = { /* do something */ }) {
//                        Icon(
//                            imageVector = Icons.Filled.Menu,
//                            contentDescription = null,
//                            tint = Color.White
//                        )
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(0xff30058C),
//
//
//                    titleContentColor = Color.White,
//                ),
//            )
//        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xffE6EFF0))

        ) {


            LazyColumn {
                item {

                    Box(modifier = Modifier.fillMaxSize()) {
                        
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(10.dp)
                                .padding(top = 10.dp)

                        ) {
                            Text(
                                modifier = Modifier
                                    .clickable {
                                        navController.navigate(ROUTE_HOME) {
                                            popUpTo(ROUTE_HOME) { inclusive = true }
                                        }
                                    },
                                text = "FOODSHARECONNECT",
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Blue

                            )


                            Text(
                                text = "Connecting Food Banks,Donors, & Partner Agencies." +
                                        "All in One APP",
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Image(
                                painter = painterResource(id = R.drawable.mobilephones),
                                contentDescription = "mobilephones",
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(20.dp))


                            Text(

                                text = "The new FoodShare Connect app makes it easy to rescue food." +
                                        "Download it today to help nourish your community!"
                            )

                            Button(onClick = {
                                navController.navigate(ROUTE_HOME) {
                                    popUpTo(ROUTE_HOME) { inclusive = true }
                                }
                            }) {

                                Text(text = "HOME")

                            }



                            Card(
                                colors = CardDefaults.cardColors(Color(0xffE52C04)),
                                elevation = CardDefaults.cardElevation(5.dp)

                            ) {


                            }




                            Button(onClick = {
                                navController.navigate(ROUTE_HOW_IT_WORKS) {
                                    popUpTo(ROUTE_HOME) { inclusive = true }
                                }
                            }) {

                                Text(text = "HOW IT WORKS")

                            }


                            Button(onClick = {
                                navController.navigate(ROUTE_DONATE_FOOD) {
                                    popUpTo(ROUTE_HOME) { inclusive = true }
                                }
                            }) {

                                Text(text = "DONATE FOOD")

                            }



                            Spacer(modifier = Modifier.height(10.dp))



                            OutlinedButton(onClick = {

                                navController.navigate(ROUTE_LOGIN) {
                                    popUpTo(ROUTE_HOME) { inclusive = true }
                                }

                            }) {

                                Text(text = "LOGIN")


                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Image(
                                painter = painterResource(id = R.drawable.first),
                                contentDescription = "first",
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Image(
                                painter = painterResource(id = R.drawable.second),
                                contentDescription = "second",
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Image(
                                painter = painterResource(id = R.drawable.third),
                                contentDescription = "third",
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(20.dp))


                        }


                    }

                }

            }


        }
        }
    }

@Composable
fun BottomBar() {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(elevation = 10.dp) {

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Home,"")
        },
            label = { Text(text = "Home") },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0
            }
        )

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Favorite,"")
        },
            label = { Text(text = "Donate") },
            selected = (selectedIndex.value == 1),
            onClick = {
                selectedIndex.value = 1
            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Person,"")
        },
            label = { Text(text = "Log in") },
            selected = (selectedIndex.value == 2),
            onClick = {
                selectedIndex.value = 2
            })
    }

}







    @Preview(showBackground = true)
    @Composable
    fun PreviewLight() {
        HomeScreen(rememberNavController())
    }



