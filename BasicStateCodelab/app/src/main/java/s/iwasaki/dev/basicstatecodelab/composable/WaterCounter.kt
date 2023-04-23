package s.iwasaki.dev.basicstatecodelab.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun WaterCounter(state: WaterCounterState, listener: WaterCounterState.Listener, modifier: Modifier = Modifier) {
    SideEffect { println("[TEST] compose WaterCounter") }

    Column(modifier = modifier.padding(16.dp)) {
        if (state.count > 0) {
            Text("You've had ${state.count} glasses.")
        }
        Button(onClick = listener.onIncrement, Modifier.padding(top = 8.dp), enabled = state.count < 10) {
            Text("Add one")
        }
    }
}

@Immutable
data class WaterCounterState(
    val count: Int
) {
    data class Listener(
        val onIncrement: () -> Unit
    )

    companion object {
        val initialState = WaterCounterState(
            count = 0
        )
    }
}