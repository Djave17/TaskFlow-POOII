package com.taskflow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import androidx.compose.ui.Modifier
import com.taskflow.ui.screen.SplashScreen
import com.taskflow.ui.screen.TaskDetailScreen
import com.taskflow.ui.screen.TaskListScreen

@Composable
fun AppNavigation(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Splash,
        modifier = modifier
    ){
        composable<Splash> {
            SplashScreen(navController = navController)
        }
        composable<TaskList> {
            TaskListScreen(navController = navController)
        }
        composable<TaskDetail> { backStackEntry ->
            val route = backStackEntry.toRoute<TaskDetail>()
            TaskDetailScreen(navController = navController, taskId = route.taskId)
        }
    }
}
