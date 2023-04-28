package s.iwasaki.dev.basicstatecodelab

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import s.iwasaki.dev.basicstatecodelab.composable.WellnessScreen

@Composable
fun WellnessApp(

) {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = "initialScreen") {
        composable(
            route = "initialScreen"
        ) {
            WellnessScreen(
                screenName = "initial screen",
                onNavigate = { navController.navigate("screenA?screenName=WellnessScreenA") })
        }

        composable(
            route = "screenA?screenName={screenName}",
            arguments = listOf(navArgument("screenName") { type = NavType.StringType; nullable = true })
        ) { backStackEntry ->
            WellnessScreen(
                screenName = backStackEntry.arguments?.getString("screenName") ?: "screen name",
                onNavigate = { navController.navigate("screenB?screenName=WellnessScreenB") }
            )
        }

        composable(
            route = "screenB?screenName={screenName}",
            arguments = listOf(navArgument("screenName") { type = NavType.StringType; nullable = true })
        ) { backStackEntry ->
            WellnessScreen(
                screenName = backStackEntry.arguments?.getString("screenName") ?: "screen name",
                onNavigate = { navController.navigate("screenC?screenName=WellnessScreenC") }
            )
        }

        composable(
            route = "screenC?screenName={screenName}",
            arguments = listOf(navArgument("screenName") { type = NavType.StringType; nullable = true })
        ) { backStackEntry ->
            WellnessScreen(
                screenName = backStackEntry.arguments?.getString("screenName") ?: "screen name",
                onNavigate = { navController.navigate("screenA?screenName=WellnessScreenA") }
            )
        }
    }
}