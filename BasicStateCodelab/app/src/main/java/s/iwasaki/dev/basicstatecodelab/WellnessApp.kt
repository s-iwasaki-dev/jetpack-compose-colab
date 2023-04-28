package s.iwasaki.dev.basicstatecodelab

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
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
                onNavigate = { navController.navigate("screenA?args=${it.parseToString()}") }
            )
        }

        composable(
            route = "screenA?args={args}",
            arguments = listOf(navArgument("args") { nullable = true })
        ) { backStackEntry ->
            WellnessScreen(
                args = backStackEntry.arguments?.getString("args")?.parseToPayload(),
                onNavigate = { navController.navigate("initialScreen") }
            )
        }
    }
}