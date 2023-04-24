package s.iwasaki.dev.basicstatecodelab.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun WaterCounter(state: WaterCounterState, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    SideEffect { println("[TEST] compose WaterCounter") }

    Column(modifier = modifier.padding(16.dp)) {
        if (state.count > 0) {
            Text("You've had ${state.count} glasses.")
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = state.count < 10) {
            Text("Add one")
        }
    }
}

data class WaterCounterState(
    val count: Int
) {
    companion object {
        val initialState = WaterCounterState(
            count = 0
        )
    }
}