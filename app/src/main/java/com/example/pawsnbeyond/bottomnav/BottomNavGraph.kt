package com.example.pawsnbeyond.bottomnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pawsnbeyond.screen.AccountScreen
import com.example.pawsnbeyond.screen.CategoryScreen
import com.example.pawsnbeyond.screen.ConsultScreen
import com.example.pawsnbeyond.screen.HomeScreen

@Composable
fun BottomNavGraph(
    navController: NavController
){
    NavHost(
        navController = navController as NavHostController,
        startDestination = BottomBarScreen.Home.route
    ){
        composable(route = BottomBarScreen.Home.route){
            HomeScreen()
        }
        composable(route = BottomBarScreen.Category.route){
            CategoryScreen()
        }
        composable(route = BottomBarScreen.Consult.route){
            ConsultScreen()
        }
        composable(route = BottomBarScreen.Account.route){
            AccountScreen()
        }
    }
}