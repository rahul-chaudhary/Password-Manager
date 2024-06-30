package com.example.chaintechtest

import android.content.Context
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chaintechtest.ui.theme.clrBlack
import com.example.chaintechtest.ui.theme.clrBlueMedium
import com.example.chaintechtest.ui.theme.clrGrey
import com.example.chaintechtest.ui.theme.clrRed
import com.example.chaintechtest.ui.theme.clrWhiteDark
import com.example.chaintechtest.ui.theme.clrWhiteFaded
import com.example.chaintechtest.ui.theme.clrWhiteNormal

@Preview
@Composable
fun HomePagePreview() {
    HomePage(LocalContext.current)
}

@Preview
@Composable
fun PasswordManagerBottomSheetPreview() {
    val showBottomSheet = remember { mutableStateOf(true) }
    val bottomSheetAction = remember { mutableStateOf(BottomSheetAction.FAB_ACTION) }
    val tfValue = remember { mutableStateOf("") }
    PasswordManagerBottomSheet(showBottomSheet, bottomSheetAction)
}

@Composable
fun HomePage(context: Context) {
    val cardTitleList = listOf("Google", "Facebook", "Instagram", "Twitter", "LinkedIn")
    val showBottomSheet = remember { mutableStateOf(false) }
    val bottomSheetAction = remember { mutableStateOf(BottomSheetAction.NO_ACTION) }

    Scaffold(
        containerColor = clrWhiteDark,
        floatingActionButton = { HomeFAB(showBottomSheet, bottomSheetAction) }

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
                    PasswordItemCard(it, context, showBottomSheet, bottomSheetAction)
                }

            }
            PasswordManagerBottomSheet(showBottomSheet, bottomSheetAction )
        }
    }
}

@Composable
private fun PasswordItemCard(cardTitle: String, context: Context, showBottomSheet: MutableState<Boolean>, action: MutableState<BottomSheetAction>) {
    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 15.dp)
            .fillMaxWidth(1f)
            .background(color = clrWhiteNormal, shape = RoundedCornerShape(30.dp))
            .shadow(
                elevation = 0.5.dp,
                shape = RoundedCornerShape(0.5.dp)
            )
            .clickable {
                showBottomSheet.value = true
                action.value = BottomSheetAction.CARD_CLICK_ACTION
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
                color = clrGrey,
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
private fun HomeFAB(
    showBottomSheet: MutableState<Boolean>,
    action: MutableState<BottomSheetAction>
) {
    FloatingActionButton(
        containerColor = clrBlueMedium,
        contentColor = clrWhiteDark,
        modifier = Modifier
            .height(80.dp)
            .width(80.dp),
        onClick = {
            action.value = BottomSheetAction.FAB_ACTION
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
private fun PasswordManagerBottomSheet(
    showBottomSheet: MutableState<Boolean>,
    action: MutableState<BottomSheetAction>,

) {
    val sheetState = rememberModalBottomSheetState()

    if (showBottomSheet.value) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet.value = false
            },
            sheetState = sheetState,
            containerColor = clrWhiteFaded,
        ) {
            when (action.value) {

                BottomSheetAction.FAB_ACTION -> {
                    AddAccountDetails()
                }

                BottomSheetAction.CARD_CLICK_ACTION -> {
                    EditAccountDetails()
                }
                BottomSheetAction.NO_ACTION -> {}
            }
        }
    }
}

@Composable
fun EditAccountDetails() {
    Column(horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(horizontal = 15.dp, vertical = 0.dp)
        ) {
        Text(
            "Account Details", fontSize = 25.sp, color = clrBlueMedium, modifier = Modifier.padding(vertical = 30.dp))
        Text(text = "Account type", fontSize = 10.sp, color = clrGrey)
        Text(text = "Facebook", fontSize = 20.sp, color = clrBlack, modifier = Modifier.padding(bottom = 20.dp))
        Text(text = "Username/Email", fontSize = 10.sp, color = clrGrey)
        Text(text = "random@email.com", fontSize = 20.sp, color = clrBlack, modifier = Modifier.padding(bottom = 20.dp))
        Text(text = "Password", fontSize = 10.sp, color = clrGrey)
        Text(text = "********", fontSize = 20.sp, color = clrBlack, modifier = Modifier.padding(bottom = 20.dp))
        Row(horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth(1f)) {
            ElevatedButton(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = clrBlack
                ),
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .weight(0.5f)) {
                Text(text ="Edit", color = clrWhiteNormal)
            }
            ElevatedButton(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = clrRed
                ),
                modifier = Modifier
                    .padding(5.dp)
                    .weight(0.5f)
            ) {
                Text(text ="Delete", color = clrWhiteNormal)
            }

        }


    }
}

@Composable
private fun AddAccountDetails() {
    val isErrorInAccountName = rememberSaveable { mutableStateOf(false) }
    val isErrorInUserName = rememberSaveable { mutableStateOf(false) }
    val isErrorInPassword = rememberSaveable { mutableStateOf(false) }
    val accountTF = remember { mutableStateOf("") } //account text field
    val userNameTF = remember { mutableStateOf("") } //username / email text field
    val passwordTF = remember { mutableStateOf("") } //password text field

    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Account Name TextField
        BottomSheetTextField(
            tfValue = accountTF,
            visualTransformation = VisualTransformation.None,
            isErrorInField = isErrorInAccountName,
            placeholderStr = "Account Name",
            supportingText = "Account Name can't be empty"
        )
        //UserName/Email TextField
        BottomSheetTextField(
            tfValue = userNameTF,
            visualTransformation = VisualTransformation.None,
            isErrorInField = isErrorInUserName,
            placeholderStr = "Username/Email",
            supportingText = "Enter valid email address"
        )
        //Password TextField
        BottomSheetTextField(
            tfValue = passwordTF,
            visualTransformation = VisualTransformation.None,
            isErrorInField = isErrorInPassword,
            placeholderStr = "Password",
            supportingText = "Password length must be greater than 8"
        )
        //Add Account Button
        ElevatedButton(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .height(50.dp)
                .fillMaxWidth(1f),
            colors = ButtonDefaults.elevatedButtonColors(
               containerColor = clrBlack
            ),
            onClick = { /*TODO*/ }) {

            Text("Add New Account", color = clrWhiteNormal, fontSize = 15.sp)
        }


    }
}

@Composable
private fun BottomSheetTextField(
    tfValue: MutableState<String>,
    visualTransformation: VisualTransformation,
    isErrorInField: MutableState<Boolean>,
    placeholderStr: String,
    supportingText: String
) {

    OutlinedTextField(
        value = tfValue.value,
        onValueChange = {
            tfValue.value = it
            isErrorInField.value = it.isEmpty()
        },
        singleLine = true,
        placeholder = { Text(placeholderStr) },
//        label = { Text("Password") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        modifier = Modifier.padding(vertical = 10.dp),
        visualTransformation = visualTransformation,

        supportingText = {
            if(isErrorInField.value) {
                Text(text = supportingText, fontSize = 14.sp)
            }

        },

        isError = isErrorInField.value,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = clrBlueMedium,
            unfocusedTextColor = clrGrey,
            focusedContainerColor = clrWhiteNormal,
            unfocusedContainerColor = clrWhiteNormal,
            disabledContainerColor = clrGrey,
            cursorColor = clrBlueMedium,
            focusedBorderColor = clrBlueMedium,
            unfocusedBorderColor = clrGrey,
            focusedLeadingIconColor = clrBlueMedium,
            unfocusedLeadingIconColor = clrGrey,
            focusedLabelColor = clrBlueMedium,
            unfocusedLabelColor = clrGrey,
            focusedPlaceholderColor = clrBlueMedium,
            unfocusedPlaceholderColor = clrGrey,
            focusedTrailingIconColor = clrBlueMedium,
            unfocusedTrailingIconColor = clrGrey,
        ),
    )
}

enum class BottomSheetAction {
    FAB_ACTION,
    CARD_CLICK_ACTION,
    NO_ACTION
}

