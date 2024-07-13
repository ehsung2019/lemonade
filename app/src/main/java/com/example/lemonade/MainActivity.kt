package com.example.lemonade

import kotlin.random.Random
import androidx.compose.foundation.clickable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {

                    AppName(
                        name = "Lemonade",
                        modifier = Modifier.fillMaxSize()
                    )

                LemonadeWithTextAndImage()

            }
        }
    }
}

@Composable
fun AppName(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
                .padding(vertical = 18.dp)
        ) {
            Text(
                text = name,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
@Composable
fun LemonadeWithTextAndImage(modifier:Modifier=Modifier)
{

    var result by remember {mutableStateOf(1)}
    val imageResource = when (result){
        1->R.drawable.lemon_tree
        2->R.drawable.lemon_squeeze
        3->R.drawable.lemon_drink
        else->R.drawable.lemon_restart
    }

    var instruction = when (result){
        1-> stringResource(R.string.tab_lemon_tree)
        2-> stringResource(R.string.tab_lemon_squeeze)
        3->stringResource(R.string.tab_lemon_drink)
        else->stringResource(R.string.tab_empty_glass)
    }

    var description = when (result){
        1-> stringResource(R.string.lemon_tree_description)
        2-> stringResource(R.string.lemon_squeeze_description)
        3->stringResource(R.string.lemonade_drink_description)
        else->stringResource(R.string.lemonade_restart_description)
    }

    var tabNeeded by remember {mutableStateOf(0)}
    var currentTaps by remember {mutableStateOf(0)}

    Column(
        modifier=Modifier
            .fillMaxSize(),
        verticalArrangement=Arrangement.Center,
        horizontalAlignment=Alignment.CenterHorizontally
    ){
        Box(

            modifier=Modifier
                .background(Color(0xffbffeb7), shape= RoundedCornerShape(16.dp))
                .height(200.dp)
                .width(200.dp)
                .fillMaxWidth(fraction=0.5f)
                .border()
                .align(Alignment.CenterHorizontally)

            .clickable {

                    if (result==2) {
                        if (tabNeeded == 0) {
                            tabNeeded = Random.nextInt(2, 5)
                        }
                        currentTaps++
                        if (currentTaps >= tabNeeded) {
                            result = 3
                            currentTaps = 0
                            tabNeeded = 0
                        }
                    }else {result= if(result<4) result + 1 else 1}
                       },
        contentAlignment = Alignment.Center
        ){
            Image(
                painter=painterResource(imageResource),
                contentDescription= description)
        }
        Spacer(modifier=Modifier.height(16.dp))
        Text(instruction,fontSize=18.sp)
    }
    }