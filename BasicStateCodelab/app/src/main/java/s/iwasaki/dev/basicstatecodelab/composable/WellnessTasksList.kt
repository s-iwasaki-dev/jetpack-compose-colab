
import android.os.Parcelable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.parcelize.Parcelize

@Composable
fun WellnessTasksList(
    state: WellnessTasksListState,
    listener: WellnessTasksListState.Listener,
    modifier: Modifier = Modifier
) {
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

@Parcelize
data class WellnessTasksListState(
    val list: List<WellnessTaskItemState>
) : Parcelable {
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