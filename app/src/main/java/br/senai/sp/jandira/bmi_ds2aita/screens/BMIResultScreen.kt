package br.senai.sp.jandira.bmi_ds2aita.screens

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.bmi_ds2aita.R
import br.senai.sp.jandira.bmi_ds2aita.calcs.bmiCalculate
import br.senai.sp.jandira.bmi_ds2aita.model.BmiStatus
import br.senai.sp.jandira.bmi_ds2aita.utils.numberConvertToLocale

@Composable
fun BMIResultScreen(navegacao: NavHostController?) {

    val userFile = LocalContext.current
        .getSharedPreferences("user_file", Context.MODE_PRIVATE)

    val userHeight = userFile.getFloat("user_height", 0.0f)
    val userWeight = userFile.getFloat("user_weight", 0.0f)
    val userAge = userFile.getInt("user_age", 0)

    // Obter os dados do imc do usuário
    val result = bmiCalculate(
        userWeight.toInt(),
        userHeight.toDouble().div(100)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        Color(0xFF7CD9BF),
                        Color(0xFFC0EEDA),
                    )
                )
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.your_result),
                fontSize = 40.sp,
                color = Color.White,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 16.dp)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(5f),
                shape = RoundedCornerShape(
                    topStart = 50.dp,
                    topEnd = 50.dp
                ),
                colors = CardDefaults
                    .cardColors(
                        containerColor = Color.White
                    )
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .size(130.dp),
                        colors = CardDefaults
                            .cardColors(containerColor = Color.White),
                        shape = CircleShape,
                        border = BorderStroke(
                            width = 4.dp,
                            color = result.color
                        )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Text(
                                text = numberConvertToLocale(result.bmi.second),
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Text(
                        text = result.bmi.first,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = result.color,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Card(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                            .height(80.dp),
                        colors = CardDefaults
                            .cardColors(
                                containerColor = Color.LightGray
                            )
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxSize()
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                            ) {
                                Text(
                                    text = stringResource(R.string.age)
                                )
                                Text(
                                    text = "$userAge",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            VerticalDivider(modifier = Modifier.fillMaxHeight())
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                            ) {
                                Text(
                                    text = stringResource(R.string.weight)
                                )
                                Text(
                                    text = "$userWeight Kg",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            VerticalDivider(modifier = Modifier.fillMaxHeight())
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                            ) {
                                Text(
                                    text = stringResource(R.string.height)
                                )
                                Text(
                                    text = "$userHeight",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 16.dp)
                            .background(
                                Color.Transparent
                            )
                            .fillMaxWidth()
                            .height(250.dp)
                    ) {
                        BmiLevel(
                            leftText = "Ünderweight",
                            righText = "< 18.5",
                            bulletColor = colorResource(R.color.light_blue),
                            background = if(result.status == BmiStatus.UNDER_WEIGHT) colorResource(R.color.light_blue) else Color.Transparent
                        )
                        BmiLevel(
                            leftText = "Normal",
                            righText =  "18.6 - 24.9",
                            bulletColor = colorResource(R.color.light_green),
                            background = if(result.status == BmiStatus.UNDER_WEIGHT) colorResource(R.color.light_green) else Color.Transparent
                        )
                        BmiLevel(
                            leftText = "Overweigth",
                            righText =  "25.0 - 29.9",
                            bulletColor = colorResource(R.color.yellow),
                            background = if(result.status == BmiStatus.UNDER_WEIGHT) colorResource(R.color.yellow) else Color.Transparent

                        )
                        BmiLevel(
                            leftText = "Obesity Class I",
                            righText =  "30.0 - 34.9",
                            bulletColor = colorResource(R.color.yellow),
                            background = if(result.status == BmiStatus.UNDER_WEIGHT) colorResource(R.color.yellow) else Color.Transparent

                        )
                        BmiLevel(
                            leftText = "Obesity Class II",
                            righText =  "35.0 - 39.9",
                            bulletColor = colorResource(R.color.light_orange),
                            background = if(result.status == BmiStatus.UNDER_WEIGHT) colorResource(R.color.light_orange) else Color.Transparent
                        )
                        BmiLevel(
                            leftText = "Obesity Class III",
                            righText =  ">39.9",
                            bulletColor = colorResource(R.color.dark_orange),
                            background = if(result.status == BmiStatus.UNDER_WEIGHT) colorResource(R.color.dark_orange) else Color.Transparent
                        )

                    }
                    HorizontalDivider()
                    Button(
                        onClick = {},
                        colors =  ButtonDefaults.buttonColors(containerColor = Color.Blue),
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp)

                    ) {
                        Text(
                            text = stringResource(R.string.new_calculate),
                            fontSize = 20.sp
                        )
                    }
                }

            }
        }
    }
}

@Preview
@Composable
private fun BMIResultScreenPreview() {
    BMIResultScreen(null)
}