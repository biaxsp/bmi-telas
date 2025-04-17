package br.senai.sp.jandira.bmi_ds2aita.model

import androidx.compose.ui.graphics.Color

data class Bmi(
    var color: Color = Color.Transparent,
    var status: BmiStatus = BmiStatus.NORMAL,
    var bmi: Pair<String, Double>
)
