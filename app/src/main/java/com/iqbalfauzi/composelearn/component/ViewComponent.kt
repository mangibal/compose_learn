package com.iqbalfauzi.composelearn.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iqbalfauzi.composelearn.MyApp

/**
 * Created by Iqbal Fauzi on 20/04/21 20.28
 * iqbal.fauzi.if99@gmail.com
 */
@Composable
fun MyScreenContent(names: List<String> = List(1000) { "Hello Android #$it" }) {

    val counterState = remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxHeight()) {
        NameList(names, Modifier.weight(1f))
        Counter(
            count = counterState.value,
            updateCount = { newCount ->
                counterState.value = newCount
            }
        )
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names) { name ->
            Greeting(name = name)
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    Button(
        onClick = { updateCount(count + 1) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = when {
                count in 6..10 -> Color.Green
                count in 11..20 -> Color.Blue
                count in 21..25 -> Color.Yellow
                count > 25 -> Color.Red
                else -> Color.White
            }
        )
    ) {
        Text("I've been clicked $count times")
    }
}

@Composable
fun Greeting(name: String) {
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(if (isSelected) Color.Red else Color.Transparent)
    Text(
        text = "Hello $name!",
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(color = backgroundColor)
            .clickable(onClick = { isSelected = !isSelected }),
        style = MaterialTheme.typography.body1
    )
}

@Preview(showBackground = true, name = "Text Preview")
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}