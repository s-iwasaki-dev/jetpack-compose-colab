package s.iwasaki.dev.basicstatecodelab

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
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
                onNavigate = { navController.navigate("screenA?payload=${
                    Json.encodeToString(WellnessScreenPayload(
                        screenName = "WellnessScreenA"
                    ))
                }") }
            )
        }

        composable(
            route = "screenA?payload={payload}",
            arguments = listOf(navArgument("payload") { type = NavType.StringType; nullable = true })
        ) { backStackEntry ->
            WellnessScreen(
                payload = backStackEntry.arguments?.getString("payload")?.let { Json.decodeFromString<WellnessScreenPayload>(it) },
                onNavigate = { navController.navigate("screenB?payload=${
                    Json.encodeToString(WellnessScreenPayload(
                        screenName = "WellnessScreenB"
                    ))
                }") }
            )
        }

        composable(
            route = "screenB?payload={payload}",
            arguments = listOf(navArgument("payload") { type = NavType.StringType; nullable = true })
        ) { backStackEntry ->
            WellnessScreen(
                payload = backStackEntry.arguments?.getString("payload")?.let { Json.decodeFromString<WellnessScreenPayload>(it) },
                onNavigate = { navController.navigate("screenC?payload=${
                    Json.encodeToString(WellnessScreenPayload(
                        screenName = "WellnessScreenC"
                    ))
                }") }
            )
        }

        composable(
            route = "screenC?payload={payload}",
            arguments = listOf(navArgument("payload") { type = NavType.StringType; nullable = true })
        ) { backStackEntry ->
            WellnessScreen(
                payload = backStackEntry.arguments?.getString("payload")?.let { Json.decodeFromString<WellnessScreenPayload>(it) },
                onNavigate = { navController.navigate("screenA?payload=${
                    Json.encodeToString(WellnessScreenPayload(
                        screenName = "WellnessScreenA"
                    ))
                }") }
            )
        }
    }
}