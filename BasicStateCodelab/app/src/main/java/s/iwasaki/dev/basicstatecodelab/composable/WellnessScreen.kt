package s.iwasaki.dev.basicstatecodelab.composable

import WellnessTasksList
import WellnessTasksListState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import s.iwasaki.dev.basicstatecodelab.WellnessScreenPayload
import s.iwasaki.dev.basicstatecodelab.WellnessScreenViewModel

@Composable
fun WellnessScreen(
    payload: WellnessScreenPayload?,
    onNavigate: () -> Unit,
    viewModel: WellnessScreenViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    WellnessScreen(
        screenName = payload?.screenName ?: "no name screen",
        onNavigate = onNavigate,
        state = viewModel.viewState,
        onIncrement = { viewModel.onIncrement() },
        onCloseTask = { id -> viewModel.onCloseTask(id) },
        onCheckedTask = { id, checked -> viewModel.onCheckedTask(id, checked) },
        modifier = modifier
    )
}

@Composable
fun WellnessScreen(
    screenName: String,
    onNavigate: () -> Unit,
    state: WellnessScreenState,
    onIncrement: () -> Unit,
    onCloseTask: (Int) -> Unit,
    onCheckedTask: (Int, Boolean) -> Unit,
    modifier: Modifier
) {
    SideEffect { println("[TEST] compose WellnessScreen") }

    Column(modifier = modifier) {
        Row {
            Text(text = screenName)
            Button(onClick = onNavigate) {
                Text(text = "navigate")
            }
        }

        WaterCounter(
            state = state.waterCounterState,
            onIncrement = onIncrement,
            modifier = modifier
        )

        WellnessTasksList(
            state = state.wellnessTasksListState,
            onCloseTask = onCloseTask,
            onCheckedTask = onCheckedTask,
            modifier = modifier
        )
    }
}

data class WellnessScreenState(
    val waterCounterState: WaterCounterState,
    val wellnessTasksListState: WellnessTasksListState
) {
    companion object {
        val initialState = WellnessScreenState(
            waterCounterState = WaterCounterState.initialState,
            wellnessTasksListState = WellnessTasksListState.initialState
        )
    }
}

