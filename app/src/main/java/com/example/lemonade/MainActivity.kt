package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

var currentState: MutableState<Int> = mutableIntStateOf(1)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Surface {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeHeader(){

    Row(
        Modifier
            .background(Color(0xFFF9E44C))
            .fillMaxWidth()
            .height(50.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            color = Color(0xff333333)
        )
    }
}

@Composable
fun LemonScreen(
    modifier: Modifier = Modifier,
    imageRes: Int,
    contentDescriptionId: Int,
    textResId: Int,
    onClick: () -> Unit
){
    Column(modifier = modifier){
        LemonadeHeader()

        Column(
            modifier = modifier.wrapContentSize(Alignment.Center),
            verticalArrangement =  Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(

                onClick = { onClick },
                shape = RoundedCornerShape(48.dp),
                colors = ButtonDefaults
                    .buttonColors(Color(0xFFC3ECD2))
            ) {

                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = stringResource(id = contentDescriptionId)
                )
            }
            Text(
                text = stringResource(id = textResId),
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                color = Color(0xff3C3B2A)
            )
        }
    }
}

@Composable
fun LemonadeTree(modifier: Modifier = Modifier){
    Column(modifier = modifier){

        LemonScreen(
            imageRes = R.drawable.lemon_tree,
            contentDescriptionId = R.string.lemon_tree_content_description,
            textResId = R.string.tap_tree,
            onClick = {currentState.value = 2},
            modifier = modifier.wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun Lemon(modifier: Modifier = Modifier){
    var ans by remember { mutableIntStateOf(1)}
    Column(modifier = modifier){

        LemonScreen(
            imageRes = R.drawable.lemon_squeeze,
            contentDescriptionId = R.string.lemon_content_description,
            textResId = R.string.tap_lemon,
            onClick = {val result = (2..4).random()
                    if (result == ans || ans >= 4 ){
                        currentState.value = 3
                    }else {
                        ans++
                    }
            },
            modifier = modifier.wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun GlassOfLemonade (modifier: Modifier = Modifier){
    Column(modifier = modifier){

        LemonScreen(
            imageRes = R.drawable.lemon_drink,
            contentDescriptionId = R.string.lemonade_content_description,
            textResId = R.string.tap_lemonade,
            onClick = { currentState.value = 4 },
            modifier = modifier.wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun EmptyGlass (modifier: Modifier = Modifier){
    Column(modifier = modifier){

        LemonScreen(
            imageRes = R.drawable.lemon_restart,
            contentDescriptionId = R.string.empty_glass_content_description,
            textResId = R.string.tap_empty_glass,
            onClick = { currentState.value = 1  },
            modifier = modifier.wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun LemonadeApp() {

    when (currentState.value){
        1 -> LemonadeTree(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffF5F5DC)))
        2 -> Lemon(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffF5F5DC)))
        3 -> GlassOfLemonade(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffF5F5DC)))
        4 -> EmptyGlass(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffF5F5DC)))
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}