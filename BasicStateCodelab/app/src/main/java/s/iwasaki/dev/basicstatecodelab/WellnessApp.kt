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
                payload = WellnessScreenPayload("initial screen"),
                onNavigate = { navController.navigate("screenA?payload=${it.parseToString()}") }
            )
        }

        composable(
            route = "screenA?payload={payload}",
            arguments = listOf(navArgument("payload") { type = NavType.StringType; nullable = true })
        ) { backStackEntry ->
            WellnessScreen(
                payload = backStackEntry.arguments?.getString("payload")?.parseToPayload(),
                onNavigate = { navController.navigate("initialScreen") }
            )
        }
    }
}