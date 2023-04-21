package s.iwasaki.dev.basicstatecodelab

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.parcelize.Parcelize

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        var count by rememberSaveable { mutableStateOf(0) }
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = { count++ }, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

@Composable
fun StatelessCounter(state: StatelessCounterComposableState, listener: StatelessCounterComposableState.Listener, modifier: Modifier = Modifier) {
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
data class StatelessCounterComposableState(
    val count: Int
) : Parcelable {
    data class Listener(
        val onIncrement: () -> Unit
    )

    companion object {
        val initialState = StatelessCounterComposableState(
            count = 0
        )
    }
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var state by rememberSaveable { mutableStateOf(StatelessCounterComposableState.initialState) }
    StatelessCounter(state, StatelessCounterComposableState.Listener(
        onIncrement = { state = state.copy(count = state.count + 1) }
    ), modifier)
}