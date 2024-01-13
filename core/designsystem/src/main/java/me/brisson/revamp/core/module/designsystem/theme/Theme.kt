package me.brisson.revamp.core.module.designsystem.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val darkColorScheme = darkColorScheme(
    primary = darkPrimary,
    background = darkBackground,
    surface = darkSurface,
    onSurface = darkPrimary,
    onBackground = darkPrimary,
)

private val lightColorScheme = lightColorScheme(
    primary = lightPrimary,
    background = lightBackground,
    surface = lightSurface,
    onSurface = lightPrimary,
    onBackground = lightPrimary,
)

@Composable
fun RevampTheme(content: @Composable () -> Unit) {
    val colorScheme = if (ThemeState.isDark) darkColorScheme else lightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                !ThemeState.isDark
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

object ThemeState {
    //TODO: save theme as preference
    var isDark by mutableStateOf(false)
}
