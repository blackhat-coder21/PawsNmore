package com.example.pawsnbeyond.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pawsnbeyond.R

@Composable
fun CategoryScreen(){
    expanList()
}

@Composable
fun expanList(names: List<String> = List(100){"$it"}){
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)){
        items(items = names){ name->
            Cardview(name)
        }
    }
}


@Composable
fun Cardview(name: String){

    val hexColor = Color(0xFFB5CC5A)
    Card(
        backgroundColor = hexColor,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ){
        CardContent(name)
    }
}

@Composable
fun CardContent(name: String) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ){
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)) {
            Text(text = "Hello, ")
            Text(text = name,
                style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.ExtraBold)
            )
            if(expanded){
                Text(text = ("Lorem ipsum casjkdjks dfdfj\t").repeat(3))
            }
        }
        CustomIconButton(expanded = expanded) {
            expanded = !expanded
        }
    }
}

@Composable
fun CustomIconButton(expanded: Boolean, onClick: () -> Unit) {
    IconButton(onClick = { onClick() }) {
        val icon: Painter = if (expanded) {
            painterResource(id = R.drawable.baseline_expand_less_24)
        } else {
            painterResource(id = R.drawable.baseline_expand_more_24)
        }
        val contentDescription = if (expanded) {
            stringResource(id = R.string.show_less)
        } else {
            stringResource(id = R.string.show_more)
        }
        Icon(painter = icon, contentDescription = contentDescription, modifier = Modifier.size(24.dp))
    }
}
