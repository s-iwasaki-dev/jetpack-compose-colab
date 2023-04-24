package s.iwasaki.dev.basicstatecodelab.composable

import WellnessTasksList
import WellnessTasksListState
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import s.iwasaki.dev.basicstatecodelab.WellnessScreenViewModel

@Composable
fun WellnessScreen(
    viewModel: WellnessScreenViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    // FIXME Listenerインスタンスの再コンポーズへの影響を意識しないといけないのは微妙
    val listener = remember {
        WellnessScreenState.Listener(
            waterCounterListener = WaterCounterState.Listener(
                onIncrement = { viewModel.onIncrement() }
            ),
            wellnessTasksListListener = WellnessTasksListState.Listener(
                onCloseTask = { id -> viewModel.onCloseTask(id) },
                onCheckedTask = { id, checked -> viewModel.onCheckedTask(id, checked) }
            )
        )
    }

    WellnessScreen(viewModel.viewState, listener, modifier)
}

@Composable
fun WellnessScreen(
    state: WellnessScreenState,
    listener: WellnessScreenState.Listener,
    modifier: Modifier
) {
    SideEffect { println("[TEST] compose WellnessScreen") }

    Column(modifier = modifier) {
        WaterCounter(
            state = state.waterCounterState,
            listener = listener.waterCounterListener,
            modifier = modifier
        )

        WellnessTasksList(
            state = state.wellnessTasksListState,
            listener = listener.wellnessTasksListListener,
            modifier = modifier
        )
    }
}

data class WellnessScreenState(
    val waterCounterState: WaterCounterState,
    val wellnessTasksListState: WellnessTasksListState
) {
    data class Listener(
        val waterCounterListener: WaterCounterState.Listener,
        val wellnessTasksListListener: WellnessTasksListState.Listener
    )

    companion object {
        val initialState = WellnessScreenState(
            waterCounterState = WaterCounterState.initialState,
            wellnessTasksListState = WellnessTasksListState.initialState
        )
    }
}

