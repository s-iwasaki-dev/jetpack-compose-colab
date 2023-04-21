package s.iwasaki.dev.basicstatecodelab.composable

import android.os.Parcelable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.parcelize.Parcelize


@Composable
fun WaterCounter(state: WaterCounterState, listener: WaterCounterState.Listener, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (state.count > 0) {
            Text("You've had ${state.count} glasses.")
        }
        Button(onClick = listener.onIncrement, Modifier.padding(top = 8.dp), enabled = state.count < 10) {
            Text("Add one")
        }
    }
}

@Parcelize
data class WaterCounterState(
    val count: Int
) : Parcelable {
    data class Listener(
        val onIncrement: () -> Unit
    )

    companion object {
        val initialState = WaterCounterState(
            count = 0
        )
    }
}