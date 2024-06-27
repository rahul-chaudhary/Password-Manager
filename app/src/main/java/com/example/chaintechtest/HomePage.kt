package com.example.chaintechtest

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chaintechtest.ui.theme.blueMedium
import com.example.chaintechtest.ui.theme.whiteDark

@Preview
@Composable
fun HomePagePreview() {
    HomePage()
}

@Composable
fun HomePage() {

    Scaffold(
        containerColor = whiteDark,
        floatingActionButton = {    HomeFAB()   }

    ) { innerPadding ->

        Column(
            Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            AppBarBox()

        }
    }
}

@Composable
private fun AppBarBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(horizontal = 15.dp, vertical = 30.dp)
            .height(80.dp)

    ) {
        Text(
            "Password Manager",
            fontSize = 24.sp,
            textAlign = TextAlign.Start
        )

    }
}

@Composable
private fun HomeFAB() {
    FloatingActionButton(
        containerColor = blueMedium,
        contentColor = whiteDark,
        modifier = Modifier
            .height(80.dp)
            .width(80.dp),
        onClick = { /*TODO*/ }) {
        Icon(
            imageVector = Icons.Filled.Add,
            modifier = Modifier
                .height(37.dp)
                .width(37.dp),
            contentDescription = "Add FAB"
        )
    }
}