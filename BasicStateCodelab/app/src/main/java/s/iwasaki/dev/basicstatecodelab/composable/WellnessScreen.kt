
import android.os.Parcelable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.parcelize.Parcelize
import s.iwasaki.dev.basicstatecodelab.composable.WaterCounter
import s.iwasaki.dev.basicstatecodelab.composable.WaterCounterState

@Composable
fun WellnessScreen(
    viewModel: WellnessScreenViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    SideEffect { println("[TEST] compose WellnessScreen") }

    val viewState by viewModel.viewState.collectAsState()
    Column(modifier = modifier) {
        WaterCounter(
            state = viewState.waterCounterState,
            listener = WaterCounterState.Listener(
                onIncrement = { viewModel.onIncrement() }
            ),
            modifier = modifier
        )

        WellnessTasksList(
            state = viewState.wellnessTasksListState,
            listener = WellnessTasksListState.Listener(
                onCloseTask = { id -> viewModel.onCloseTask(id) },
                onCheckedTask = { id, checked -> viewModel.onCheckedTask(id, checked) }
            ),
            modifier = modifier
        )
    }
}


@Parcelize
data class WellnessScreenState(
    val waterCounterState: WaterCounterState,
    val wellnessTasksListState: WellnessTasksListState
) : Parcelable {
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

