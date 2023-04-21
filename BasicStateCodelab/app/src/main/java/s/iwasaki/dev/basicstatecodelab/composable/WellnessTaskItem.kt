
import android.os.Parcelable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.parcelize.Parcelize

@Composable
fun WellnessTaskItem(
    state: WellnessTaskItemState,
    listener: WellnessTaskItemState.Listener,
    modifier: Modifier = Modifier
) {
    SideEffect { println("[TEST] compose WellnessTaskItem") }

    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = state.taskName
        )
        Checkbox(
            checked = state.checked,
            onCheckedChange = listener.onCheckedChange
        )
        IconButton(onClick = listener.onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}

@Parcelize
data class WellnessTaskItemState(
    val id: Int,
    val taskName: String,
    val checked: Boolean,
) : Parcelable {
    data class Listener(
        val onCheckedChange: (Boolean) -> Unit,
        val onClose: () -> Unit,
    )

    companion object {
        val initialState = WellnessTaskItemState(
            id = -1,
            taskName = "",
            checked = false
        )
    }
}