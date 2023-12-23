package com.example.pawsnbeyond.bottomnav

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pawsnbeyond.DrawerBody
import com.example.pawsnbeyond.DrawerHeader
import com.example.pawsnbeyond.MenuItem
import com.example.pawsnbeyond.R
import com.example.pawsnbeyond.ui.theme.Purple200
import com.example.pawsnbeyond.ui.theme.Purple500
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomNav(){
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(

        topBar = {
            TopAppbar(
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                items = listOf(
                    MenuItem(
                        id = "Home",
                        title = "Home",
                        contentDescription = "Go to home Screen",
                        icon = Icons.Default.Home
                    ),
                    MenuItem(
                        id = "Settings",
                        title = "Settings",
                        contentDescription = "Go to settings Screen",
                        icon = Icons.Default.Settings
                    ),
                    MenuItem(
                        id = "About",
                        title = "About",
                        contentDescription = "Go to about Screen",
                        icon = Icons.Default.Info
                    ),
                ),
                onItemClick = {
                    println("Clicked on ${it.title}")
                }
            )
        },
        bottomBar = {BottomBar(navController = navController)}

    ){
        BottomNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavController){
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Category,
        BottomBarScreen.Consult,
        BottomBarScreen.Account
    )

    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    Row(modifier = Modifier
        .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
        .background(Color.White)
        .fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEach{
            screen -> AddItem(
            screen = screen,
            currentDestination = currentDestination,
            navController = navController
        )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

    val background =
        if(selected) Purple500.copy(alpha = 0.6f) else Color.Transparent

    val contentColor=
        if(selected) Color.White else Color.Black

    Box(
        modifier = Modifier
            .height(45.dp)
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ){
        Row(modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            Icon(
                painter = painterResource(id = if(selected) screen.icon_focused else screen.icon),
                contentDescription = "icon",
                tint = contentColor
                )
            AnimatedVisibility(visible = selected) {
                Text(
                    text = screen.title,
                    color = contentColor
                    )
            }
        }
    }
}

@Composable
fun BottomNavPreview(){
    BottomNav()
}


@Composable
fun TopAppbar(
    onNavigationIconClick: () -> Unit
){
    TopAppBar(
        title = {
            Text(
                text = "PawsNmore",
                color = Color.Black,
                fontWeight = FontWeight.Normal
            )
        },
        backgroundColor = Color.White,
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "menu")
            }
        },
        actions = {
            Icon(painter = painterResource(id = R.drawable.baseline_campaign_24), contentDescription = "campaign")
            Spacer(modifier = Modifier.padding(horizontal = 12.dp))
            Icon(painter = painterResource(id = R.drawable.outline_shopping_cart_24), contentDescription = "shopping")
            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
        }
    )
}
