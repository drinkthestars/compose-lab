package com.goofy.goober.composelab

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun LabScaffold(
    navController: NavHostController,
    labs: List<Lab>,
    startDestination: Screen,
    navGraphBuilder: NavGraphBuilder.() -> Unit,
) {
    MaterialTheme {
        Scaffold {
            NavHost(
                modifier = Modifier.padding(it),
                navController = navController,
                startDestination = startDestination.route
            ) {
                composable(startDestination.route) {
                    val context = LocalContext.current
                    Labs(labs, onClick = { lab ->
                        when (lab) {
                            is ActivityLab<*> -> {
                                context.startActivity(Intent(context, lab.activityClass.java))
                            }
                            is ComposableLab -> {
                                navController.navigate(lab.screen.route)
                            }
                        }
                    })
                }
                navGraphBuilder()
            }
        }
    }
}

@Composable
private fun Labs(
    labs: List<Lab>,
    onClick: (Lab) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Spacer(Modifier.height(48.dp))
        }
        items(labs, key = { it.title }) { lab ->
            Button(onClick = { onClick(lab) }) {
                Text(lab.title)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Spacer(Modifier.height(48.dp))
        }
    }
}
