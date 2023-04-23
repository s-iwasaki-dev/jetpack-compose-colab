
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier

@Composable
fun WellnessTasksList(
    state: WellnessTasksListState,
    listener: WellnessTasksListState.Listener,
    modifier: Modifier = Modifier
) {
    SideEffect { println("[TEST] compose WellnessTasksList") }

    LazyColumn(modifier = modifier) {
        items(
            items = state.list,
            key = { task -> task.id }
        ) { task ->
            WellnessTaskItem(
                state = task,
                listener = WellnessTaskItemState.Listener(
                    onCheckedChange = { checked -> listener.onCheckedTask(task.id, checked) },
                    onClose = { listener.onCloseTask(task.id) }
                )
            )
        }
    }
}

@Immutable
data class WellnessTasksListState(
    val list: List<WellnessTaskItemState>
) {
    data class Listener(
        val onCheckedTask: (Int, Boolean) -> Unit,
        val onCloseTask: (Int) -> Unit
    )

    companion object {
        val initialState = WellnessTasksListState(
            list = emptyList()
        )
    }
}