
import android.os.Parcelable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.parcelize.Parcelize
import s.iwasaki.dev.basicstatecodelab.composable.WaterCounter
import s.iwasaki.dev.basicstatecodelab.composable.WaterCounterState

@Composable
fun WellnessScreen(
    viewModel: WellnessViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        WaterCounter(
            state = viewModel.viewState.waterCounterState,
            listener = WaterCounterState.Listener { viewModel.onIncrement() },
            modifier = modifier
        )

        WellnessTasksList(
            state = viewModel.viewState.wellnessTasksListState,
            listener = WellnessTasksListState.Listener(
                onCheckedTask = { task, checked ->
                    viewModel.onCheckedTask(task, checked)
                },
                onCloseTask = { task -> viewModel.onCloseTask(task) }
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

