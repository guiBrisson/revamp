package me.brisson.revamp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import me.brisson.revamp.feature.home.navigation.homeRoute
import me.brisson.revamp.feature.home.navigation.homeScreen

@Composable
fun RevampNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onShowSnackbar: suspend (String, String?) -> Boolean,
    startDestination: String = homeRoute,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        homeScreen()
    }
}
