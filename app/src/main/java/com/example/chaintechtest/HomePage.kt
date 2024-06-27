package com.example.chaintechtest

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chaintechtest.ui.theme.blueMedium
import com.example.chaintechtest.ui.theme.grey
import com.example.chaintechtest.ui.theme.whiteDark
import com.example.chaintechtest.ui.theme.whiteFaded
import com.example.chaintechtest.ui.theme.whiteNormal

@Preview
@Composable
fun HomePagePreview() {
    HomePage(LocalContext.current)
}

@Composable
fun HomePage(context: Context) {
    val cardTitleList = listOf("Google", "Facebook", "Instagram", "Twitter", "LinkedIn")
    val showBottomSheet = remember { mutableStateOf(false) }
    val bottomSheetAction = remember { mutableStateOf(BottomSheetAction.NO_ACTION) }

    Scaffold(
        containerColor = whiteDark,
        floatingActionButton = { HomeFAB(showBottomSheet) }

    ) { innerPadding ->

        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxHeight(1f),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            AppBarBox()

            LazyColumn() {
                items(cardTitleList) {
                    PasswordItemCard(it,context)
                }

            }
            PasswordManagerBottomSheet(showBottomSheet, bottomSheetAction)
        }
    }
}

@Composable
private fun PasswordItemCard(cardTitle: String,context: Context) {
    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 15.dp)
            .fillMaxWidth(1f)
            .background(color = whiteNormal, shape = RoundedCornerShape(30.dp))
            .shadow(
                elevation = 0.5.dp,
                shape = RoundedCornerShape(0.5.dp)
            )
            .clickable {
                Toast
                    .makeText(
                        context,
                        "Clicked on $cardTitle",
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(
                    horizontal = 15.dp,
                    vertical = 20.dp
                ),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = cardTitle, fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "********", fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = grey,
                letterSpacing = 1.sp,
                modifier = Modifier.padding(horizontal = 10.dp)
            )

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Arrow Right Icon"
            )
        }

    }
}

@Composable
private fun AppBarBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(horizontal = 15.dp, vertical = 30.dp)
            .height(60.dp)

    ) {
        Text(
            "Password Manager",
            fontSize = 24.sp,
            textAlign = TextAlign.Start
        )

    }
}

@Composable
private fun HomeFAB(showBottomSheet: MutableState<Boolean>) {
    FloatingActionButton(
        containerColor = blueMedium,
        contentColor = whiteDark,
        modifier = Modifier
            .height(80.dp)
            .width(80.dp),
        onClick = {
        showBottomSheet.value = true

        }) {
        Icon(
            imageVector = Icons.Filled.Add,
            modifier = Modifier
                .height(37.dp)
                .width(37.dp),
            contentDescription = "Add FAB"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PasswordManagerBottomSheet(showBottomSheet: MutableState<Boolean>, action: MutableState<BottomSheetAction>) {
    val sheetState = rememberModalBottomSheetState()

    if (showBottomSheet.value) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet.value = false
            },
            sheetState = sheetState,
            containerColor = whiteFaded,
        ) {
            when(action.value) {

                BottomSheetAction.FAB_ACTION -> {}
                BottomSheetAction.CARD_CLICK_ACTION -> {}
                BottomSheetAction.NO_ACTION -> {}
            }
        }
    }
}
enum class BottomSheetAction {
    FAB_ACTION,
    CARD_CLICK_ACTION,
    NO_ACTION
}