package net.ezra.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.ezra.ui.auth.LoginScreen
import net.ezra.ui.auth.SignupScreen
import net.ezra.ui.donate.DonateFood
import net.ezra.ui.home.HomeScreen
import net.ezra.ui.howitworks.Howitworks
import net.ezra.ui.donate.Donations
import net.ezra.ui.donate.FundsScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH


) {
    BackHandler {
        navController.popBackStack()

        }
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {


        composable(ROUTE_LOGIN) {
            LoginScreen(navController)
        }


        composable(ROUTE_SIGNUP) {
            SignupScreen(navController)
        }


        composable(ROUTE_HOME) {
            HomeScreen(navController)
        }

        composable(ROUTE_DONATE_FOOD) {
            DonateFood(navController)
        }

        composable(ROUTE_FUNDS_SCREEN) {
            FundsScreen(navController)
        }


        composable(ROUTE_HOW_IT_WORKS) {
            Howitworks(navController)
        }


        composable(ROUTE_SPLASH) {
            Donations(navController = navController, viewModel = viewModel() )
        }

































    }
}