package s.iwasaki.dev.basicstatecodelab

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import s.iwasaki.dev.basicstatecodelab.composable.WellnessScreenA
import s.iwasaki.dev.basicstatecodelab.composable.WellnessScreenB
import s.iwasaki.dev.basicstatecodelab.composable.WellnessScreenC

@Composable
fun WellnessApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "screenA") {
        composable("screenA") { WellnessScreenA(
            onNavigate = { navController.navigate("screenB") }
        ) }
        composable("screenB") { WellnessScreenB(
            onNavigate = { navController.navigate("screenC") }
        ) }
        composable("screenC") { WellnessScreenC(
            onNavigate = { navController.navigate("screenA") }
        ) }
    }
}