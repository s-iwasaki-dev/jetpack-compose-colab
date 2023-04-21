
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import s.iwasaki.dev.basicstatecodelab.composable.WaterCounterState
import s.iwasaki.dev.basicstatecodelab.domain.WellnessTask

class WellnessScreenViewModel : ViewModel() {
    var viewState by mutableStateOf(WellnessScreenState.initialState)
        private set

    // FIXME この設計だとイベントを常にViewModelに一度流す必要があり、Viewレイヤーで完結させられるものについて冗長な実装になる可能性がある
    // FIXME イベントの委譲をViewレイヤーで制御できるようにするには、ViewModelの各メソッドをpublicにしてViewから任意に呼び出せるようにしておく
    // FIXME この実装だとViewの実装からイベント処理を委譲しているViewModelのメソッドにジャンプできないのが不便
    val listener = WellnessScreenState.Listener(
        waterCounterListener = WaterCounterState.Listener(
            onIncrement = { this.onIncrement() }
        ),
        wellnessTasksListListener = WellnessTasksListState.Listener(
            onCheckedTask = { id, checked -> this.onCheckedTask(id, checked) },
            onCloseTask = { id -> this.onCloseTask(id) }
        )
    )

    init {
        viewState = viewState.copy(
            wellnessTasksListState = WellnessTasksListState(
                list = getWellnessTasks()
            )
        )
    }

    private fun onIncrement() {
        // FIXME ViewStateインスタンスを作り直すとすべてのComposableの再コンポーズが走ってしまってそう
        viewState = viewState.copy(
            waterCounterState = viewState.waterCounterState.copy(
                count = viewState.waterCounterState.count + 1
            )
        )
    }

    private fun onCloseTask(taskId: Int) {
        viewState = viewState.copy(
            wellnessTasksListState = viewState.wellnessTasksListState.copy(
                list = viewState.wellnessTasksListState.list.filterNot { it.id == taskId }
            )
        )
    }

    private fun onCheckedTask(taskId: Int, checked: Boolean) {
        // FIXME listを丸ごと作り直しているのでパフォーマンスが悪い
        viewState = viewState.copy(
            wellnessTasksListState = viewState.wellnessTasksListState.copy(
                list = viewState.wellnessTasksListState.list.map {
                    if (it.id == taskId) it.copy(checked = checked) else it
                }
            )
        )
    }
}

private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }.map {
    WellnessTaskItemState(id = it.id, taskName = it.label, checked = it.checked)
}