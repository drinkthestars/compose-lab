package com.goofy.goober.composelab.casestudy

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.goofy.goober.composelab.ComposableLab
import com.goofy.goober.composelab.LabScaffold
import com.goofy.goober.composelab.Screen

@Composable
fun CaseStudiesLab() {
    val navController = rememberNavController()

    LabScaffold(
        navController = navController,
        labs = CaseStudiesLab,
        startDestination = Screen.CaseStudies
    ) {
        composable(Screen.UiCaseStudies.PlantApp.route) { PlantApp() }
    }
}

private val CaseStudiesLab = listOf(
    ComposableLab(screen = Screen.UiCaseStudies.PlantApp)
)
