package s.iwasaki.dev.basicstatecodelab

import WellnessTaskItemState
import WellnessTasksListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import s.iwasaki.dev.basicstatecodelab.composable.WellnessScreenState
import s.iwasaki.dev.basicstatecodelab.domain.WellnessTask

class WellnessScreenViewModel : ViewModel() {
    var viewState by mutableStateOf(WellnessScreenState.initialState)
        private set

    init {
        viewState = viewState.copy(
            wellnessTasksListState = WellnessTasksListState(
                list = getWellnessTasks()
            )
        )
    }

    fun onIncrement() {
        // FIXME ViewStateインスタンスを作り直すとすべてのComposableの再コンポーズが走ってしまってそう（ログ的には走っちゃってる）
        viewState = viewState.copy(
            waterCounterState = viewState.waterCounterState.copy(
                count = viewState.waterCounterState.count + 1
            )
        )
    }

    fun onCloseTask(taskId: Int) {
        viewState = viewState.copy(
            wellnessTasksListState = viewState.wellnessTasksListState.copy(
                list = viewState.wellnessTasksListState.list.filterNot { it.id == taskId }
            )
        )
    }

    fun onCheckedTask(taskId: Int, checked: Boolean) {
        // FIXME listを丸ごと作り直しているのでパフォーマンスが悪い
        viewState = viewState.copy(
            wellnessTasksListState = viewState.wellnessTasksListState.copy(
                list = viewState.wellnessTasksListState.list.map { item ->
                    if (item.id == taskId) item.copy(checked = checked) else item
                }
            )
        )
    }
}

private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }.map {
    WellnessTaskItemState(id = it.id, taskName = it.label, checked = it.checked)
}