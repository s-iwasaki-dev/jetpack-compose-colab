
import android.os.Parcelable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
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

    Column(modifier = modifier) {
        WaterCounter(
            state = viewModel.viewState.waterCounterState,
            listener = viewModel.listener.waterCounterListener,
            modifier = modifier
        )

        WellnessTasksList(
            state = viewModel.viewState.wellnessTasksListState,
            listener = viewModel.listener.wellnessTasksListListener,
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

