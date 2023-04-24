
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier

@Composable
fun WellnessTasksList(
    state: WellnessTasksListState,
    onCheckedTask: (Int, Boolean) -> Unit,
    onCloseTask: (Int) -> Unit,
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
                onCheckedChange = { checked -> onCheckedTask(task.id, checked) },
                onClose = { onCloseTask(task.id) }
            )
        }
    }
}

data class WellnessTasksListState(
    val list: List<WellnessTaskItemState>
) {
    companion object {
        val initialState = WellnessTasksListState(
            list = emptyList()
        )
    }
}