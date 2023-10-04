package me.brisson.revamp.core.module.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import me.brisson.revamp.core.designsystem.R

val interFontFamily = FontFamily(
    Font(R.font.inter_bold, weight = FontWeight.Bold),
    Font(R.font.inter_semi_bold, weight = FontWeight.SemiBold),
    Font(R.font.inter_medium, weight = FontWeight.Medium),
    Font(R.font.inter_regular, weight = FontWeight.Normal),
    Font(R.font.inter_light, weight = FontWeight.Light),
)

// TODO: overrides default typography with interFontFamily
val Typography = Typography(
/* Default text styles to override
   bodyLarge = TextStyle(
           fontFamily = FontFamily.Default,
           fontWeight = FontWeight.Normal,
           fontSize = 16.sp,
           lineHeight = 24.sp,
           letterSpacing = 0.5.sp
   )
    titleLarge = TextStyle(
       fontFamily = FontFamily.Default,
       fontWeight = FontWeight.Normal,
       fontSize = 22.sp,
       lineHeight = 28.sp,
       letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
       fontFamily = FontFamily.Default,
       fontWeight = FontWeight.Medium,
       fontSize = 11.sp,
       lineHeight = 16.sp,
       letterSpacing = 0.5.sp
)
*/
)