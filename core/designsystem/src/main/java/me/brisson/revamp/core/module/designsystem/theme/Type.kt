package me.brisson.revamp.core.module.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import me.brisson.revamp.core.designsystem.R

val interFontFamily = FontFamily(
    Font(R.font.inter_bold, weight = FontWeight.Bold),
    Font(R.font.inter_semi_bold, weight = FontWeight.SemiBold),
    Font(R.font.inter_medium, weight = FontWeight.Medium),
    Font(R.font.inter_regular, weight = FontWeight.Normal),
    Font(R.font.inter_light, weight = FontWeight.Light),
)

val merriweatherFontFamily = FontFamily(
    Font(R.font.merriweather_light, weight = FontWeight.Light),
    Font(R.font.merriweather_regular, weight = FontWeight.Normal),
    Font(R.font.merriweather_bold, weight = FontWeight.Bold),
)

// TODO: overrides default typography
val Typography = Typography(
    titleMedium = TextStyle(
        fontFamily = merriweatherFontFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
    ),
    labelMedium = TextStyle(
        fontFamily = interFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
    )
)