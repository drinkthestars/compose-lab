package com.goofy.goober.composelab

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController

@Composable
fun LabScaffold(
    labs: List<Lab>,
    startDestination: Screen,
    navGraph: NavGraphBuilder.() -> Unit,
) {
    val navController = rememberNavController()
    Surface {
        NavHost(navController, startDestination = startDestination.route) {
            composable(startDestination.route) {
                val context = LocalContext.current
                Labs(labs, onClick = {
                    when (it) {
                        is ActivityLab<*> -> {
                            context.startActivity(Intent(context, it.activityClass.java))
                        }
                        is ComposableLab -> {
                            navController.navigate(it.screen.route)
                        }
                    }
                })
            }
            navGraph()
        }
    }
}

@Composable
private fun Labs(
    labs: List<Lab>,
    onClick: (Lab) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        labs.forEach {
            Button(onClick = { onClick(it) }) {
                Text(it.title)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
