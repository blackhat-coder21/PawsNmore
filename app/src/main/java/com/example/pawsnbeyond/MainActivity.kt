package com.example.pawsnbeyond

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.pawsnbeyond.bottomnav.BottomNav
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navigation()
        }
    }
}

@Composable
fun navigation(){

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ){
        composable("splash_screen"){
            SplashScreen(navController = navController)
        }

        composable("main_screen"){
//            MainScreen(navController = navController)
            BottomNav()
        }

    }
}

@Composable
fun SplashScreen(navController: NavController){
    LaunchedEffect(key1 = true){
        delay(4000L)
        navController.navigate("main_screen")
    }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.dog_splash))

    var isVisible by remember { mutableStateOf(false) }
    var isVisible1 by remember { mutableStateOf(false) }
    var animationPlayed by remember { mutableStateOf(false) }
    var animationPlayed1 by remember { mutableStateOf(false) }

    LaunchedEffect(isVisible==false) {
        if (!animationPlayed) {
            delay(1000) // Optional delay before the animation starts
            isVisible = !isVisible
            animationPlayed = true
        }
    }

    LaunchedEffect(isVisible1==false) {
        if (!animationPlayed) {
            delay(2000) // Optional delay before the animation starts
            isVisible1 = !isVisible1
            animationPlayed1 = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp)
            .offset(y = 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier.size(300.dp),
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp)
            .offset(y = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn() + slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(1000)),
            ) {
                Text(
                    text = "PawsN",
                    fontSize = 45.sp,
                    fontFamily = FontFamily(Font(R.font.txt2))
                )
            }

            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn() + slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(1000)),
            ) {
                Text(
                    text = "more",
                    fontSize = 45.sp,
                    fontFamily = FontFamily(Font(R.font.txt3))
                )
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 66.dp)
            .offset(y = 66.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimatedVisibility(
            visible = isVisible1,
            enter = fadeIn() + slideInVertically(initialOffsetY = { -it }, animationSpec = tween(1000)),
        ) {
            Text(
                text = "Bringing Joy to Every Wag and Whisker",
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.txt7))
            )
        }
    }
}

@Composable
fun MainScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Main Screen", fontSize = 30.sp, fontWeight = FontWeight.Bold)
    }
}