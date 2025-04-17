package br.senai.sp.jandira.bmi_ds2aita.screens

import android.text.style.BackgroundColorSpan
import android.text.style.BulletSpan
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import org.w3c.dom.Text

@Composable
fun BmiLevel(
    leftText: String = "Texto da Esquerda",
    righText: String = "Texto da direita",
    bulletColor: Color = Color.LightGray,
    background: Color = Color.Transparent

) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp),

        verticalAlignment = Alignment.CenterVertically
    ){
        Card(
            modifier = Modifier
                .size(20.dp),
            shape = CircleShape,
            colors = CardDefaults
                .cardColors(

                )

        ) { }
        Spacer(modifier = Modifier.width(16.dp))
        Card(
            modifier = Modifier
            .fillMaxWidth()
                .height(32.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults
                .cardColors(
                    containerColor = background
                )
        ) {
            Row (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp),

                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween

            ){
                Text (text = leftText)
                Text (text = righText)
            }
        }
    }

}


@Preview
@Composable
private fun BmiLevelPreview() {
    BmiLevel()

}